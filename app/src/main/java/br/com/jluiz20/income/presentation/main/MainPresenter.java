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
            return FinanceLib.pmt(ratePerPeriod, numberOfPeriods, initialValue, -desiredEarnings / ratePerPeriod, false);
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


    private void detachView(MainActivityContract.View view) {
        Log.d(TAG, "detachView: " + view.toString());
        this.view = null;
    }

    MainActivityContract.View getView() {
        return view;
    }
}
