package br.com.jluiz20.income.presentation.main;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.jluiz20.income.IncomeApplication;
import br.com.jluiz20.income.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
public class MainActivity extends AppCompatActivity implements MainActivityContract.View, TextWatcher {
    private static final String TAG = "MainActivity";
    @Inject
    MainActivityContract.Presenter presenter;
    @BindView(R.id.montly_savings)
    TextView uiSavings;

    @BindView(R.id.tiet_desired_monthly_income)
    TextInputEditText uiDesiredMonthlyEarnings;

    @BindView(R.id.sb_desired_monthly_income)
    SeekBar uiDesiredMonthlyEarningsSeekBar;

    @BindView(R.id.tv_desired_monthly_income)
    TextView uiDesiredMonthlyEarningsTextView;

    @BindView(R.id.tiet_number_of_periods)
    TextInputEditText uiNumberOfPeriods;

    @BindView(R.id.tiet_rate)
    TextInputEditText uiRatePerPeriod;

    @BindView(R.id.tiet_initial_value)
    TextInputEditText uiInitialValue;
    private double desiredEarnings = 1000d;
    private double numberOfPeriods = 120;
    private double ratePerPeriod = 0.006d;
    private double initialValue = 0.0d;

    private int desiredEarningsMin = 1000;
    private int desiredEarningsMax = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((IncomeApplication) getApplication()).component().inject(this);

        setListeners();

        setInitialValues();

    }

    private void setInitialValues() {
        uiDesiredMonthlyEarnings.setText(formatCurrency(desiredEarnings));
        uiDesiredMonthlyEarnings.setSelection(uiDesiredMonthlyEarnings.getText().length());
        uiNumberOfPeriods.setText(String.valueOf(numberOfPeriods));
        uiRatePerPeriod.setText(String.valueOf(ratePerPeriod));
        uiInitialValue.setText(formatCurrency(initialValue));
        uiSavings.setText(formatCurrency(calculate()));
    }

    private double calculate() {
        if (!validate()) {
            return 0;
        }
        return presenter.calculate(ratePerPeriod, numberOfPeriods, initialValue, desiredEarnings);
    }

    private double getDouble(String text) {
        double value = 0d;
        try {
            value = Double.valueOf(text);
        } catch (Exception e) {
            Log.d(TAG, "getDouble: parse failed");
        }
        return value;
    }

    private boolean validate() {
        desiredEarnings = getDouble(uiDesiredMonthlyEarnings.getText().toString());
        if (desiredEarnings <= 0) {
            uiDesiredMonthlyEarnings.setError(getString(R.string.error_value_cannot_be_zero));
            return false;
        } else {
            uiDesiredMonthlyEarnings.setError(null);
        }
        numberOfPeriods = getDouble(uiNumberOfPeriods.getText().toString());
        if (numberOfPeriods <= 0) {
            uiNumberOfPeriods.setError(getString(R.string.error_value_cannot_be_zero));
            return false;
        } else {
            uiNumberOfPeriods.setError(null);
        }
        ratePerPeriod = getDouble(uiRatePerPeriod.getText().toString());
        if (ratePerPeriod <= 0) {
            uiRatePerPeriod.setError(getString(R.string.error_value_cannot_be_zero));
            return false;
        } else {
            uiRatePerPeriod.setError(null);
        }
        return true;
    }

    private void setListeners() {
        uiDesiredMonthlyEarnings.addTextChangedListener(this);
        uiNumberOfPeriods.addTextChangedListener(this);
        uiRatePerPeriod.addTextChangedListener(this);
        uiInitialValue.addTextChangedListener(this);

        uiDesiredMonthlyEarningsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double newValue = (seekBar.getProgress() * (desiredEarningsMax - desiredEarningsMin) / 100) + desiredEarningsMin;
                uiDesiredMonthlyEarningsTextView.setText(formatCurrency(newValue));
                uiDesiredMonthlyEarnings.setText(String.valueOf(newValue));
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private String formatCurrency(double value) {
        return String.valueOf(value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onViewPause(this);
    }

    @Override
    public void showAppVersion(String appVersion) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        uiSavings.setText(formatCurrency(calculate()));
    }
}
