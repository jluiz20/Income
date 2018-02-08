package br.com.jluiz20.income.presentation.main;


import android.util.Log;

import org.apache.poi.ss.formula.functions.FinanceLib;

import javax.inject.Inject;

import br.com.jluiz20.income.model.usecase.UseCaseCallback;
import br.com.jluiz20.income.model.usecase.version.GetAppVersion;
import br.com.jluiz20.income.presentation.BasePresenter;

/**
 * Example of presenter iImplementation fully unit tested.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
public class MainPresenter extends BasePresenter implements MainActivityContract.Presenter {

    private static final String TAG = "MainPresenter";
    @Inject
    GetAppVersion getAppVersion;

    private MainActivityContract.View view;


    private double desiredEarnings;
    private double desiredEarningsMax;
    private double desiredEarningsMin;

    private int numberOfPeriods;
    private int numberOfPeriodsMin;
    private int numberOfPeriodsMax;

    private double ratePerPeriod;
    private double ratePerPeriodMin;
    private double ratePerPeriodMax;
    private double currentValue;


    @Inject
    public MainPresenter() {
        //Empty Constructor for DI
    }

    @Override
    public void onViewResume(MainActivityContract.View view) {
        Log.d(TAG, "onViewResume: ");
        attachView(view);

        loadProperties();
    }

    private void loadProperties() {
        loadAppVersion();
    }

    void loadAppVersion() {
        Log.d(TAG, "loadAppVersion: ");
        getAppVersion.execute(new UseCaseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "loadAppVersion:  onSuccess: " + data);
                if (hasViewAttached()) {
                    view.showAppVersion(data);
                }
            }

            @Override
            public void onError(Exception e) {
                Log.d(TAG, "loadAppVersion: onError: ");
                defaultErrorHandling(TAG, e);
            }
        });
    }


    @Override
    public double calculate(double ratePerPeriod, double numberOfPeriods, double initialValue, double desiredEarnings) {
        if (ratePerPeriod > 0 && numberOfPeriods > 0 && desiredEarnings > 0) {
            return FinanceLib.pmt(ratePerPeriod / 100, numberOfPeriods, initialValue, -desiredEarnings / (ratePerPeriod / 100), false);
        }
        return 0;
    }

    private boolean hasViewAttached() {
        return view != null;
    }

    private void attachView(MainActivityContract.View view) {
        Log.d(TAG, "attachView: " + view.toString());
        this.view = view;
    }

    @Override
    public void onViewPause(MainActivityContract.View view) {
        Log.d(TAG, "onViewPaused: ");
        detachView(view);
    }

    @Override
    public void setInitialValues() {
        setDesiredEarningsInitialValue();
        setNumberOfPeriodsInitialValue();
        setRatePerPeriodInitialValue();
        setCurrentValueInitialValue();
        notifyViewSavingsPerPeriodChanged();
    }

    @Override
    public void onUserChangedDesiredEarningsSeekBar(int progress) {
        desiredEarnings = getValueFromSeekBarChange(progress, desiredEarningsMin, desiredEarningsMax);
        notifyViewDesiredEarningsChanged();
        notifyViewSavingsPerPeriodChanged();
    }

    @Override
    public void onUserChangedNumberOfPeriodsSeekBar(int progress) {
        numberOfPeriods = (int) getValueFromSeekBarChange(progress, (double) numberOfPeriodsMin, (double) numberOfPeriodsMax);
        notifyViewNumberOfPeriodsChanged();
        notifyViewSavingsPerPeriodChanged();
    }

    private void notifyViewNumberOfPeriodsChanged() {
        view.onNumberOfPeriodsChanged(numberOfPeriodsMin, numberOfPeriods, numberOfPeriodsMax);
    }

    @Override
    public void onUserChangedRatePerPeriodSeekBar(int progress) {
        ratePerPeriod = getValueFromSeekBarChange(progress, ratePerPeriodMin, ratePerPeriodMax);
        notifyViewRatePerPeriodChanged();
        notifyViewSavingsPerPeriodChanged();
    }

    private void notifyViewRatePerPeriodChanged() {
        view.onRatePerPeriodChanged(ratePerPeriodMin, ratePerPeriod, ratePerPeriodMax);
    }

    private void notifyViewSavingsPerPeriodChanged() {
        view.onSavingsPerPeriodChanged(calculate(ratePerPeriod, numberOfPeriods, currentValue, desiredEarnings));
    }

    private double getValueFromSeekBarChange(int progress, double min, double max) {
        return (progress * (max - min) / 100) + min;
    }


    private void setDesiredEarningsInitialValue() {
        desiredEarnings = 1_000;
        desiredEarningsMin = 500;
        desiredEarningsMax = 10_000;
        notifyViewDesiredEarningsChanged();
    }

    private void notifyViewDesiredEarningsChanged() {
        view.onDesiredEarningsChanged(desiredEarningsMin, desiredEarnings, desiredEarningsMax);
    }

    private void setNumberOfPeriodsInitialValue() {
        numberOfPeriods = 120;
        numberOfPeriodsMin = 10;
        numberOfPeriodsMax = 400;
        notifyViewNumberOfPeriodsChanged();
    }

    private void setRatePerPeriodInitialValue() {
        ratePerPeriod = 0.8d;
        ratePerPeriodMin = 0.0d;
        ratePerPeriodMax = 5.0d;

        notifyViewRatePerPeriodChanged();
    }

    private void setCurrentValueInitialValue() {
        currentValue = 0.0d;

        view.onCurrentValueChanged(currentValue);
    }


    private void detachView(MainActivityContract.View view) {
        Log.d(TAG, "detachView: " + view.toString());
        this.view = null;
    }

    MainActivityContract.View getView() {
        return view;
    }
}
