package br.com.jluiz20.income.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;

import br.com.jluiz20.income.IncomeApplication;
import br.com.jluiz20.income.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private static final String TAG = "MainActivity";
    @Inject
    MainActivityContract.Presenter presenter;

    @BindView(R.id.sb_desired_monthly_income)
    SeekBar uiDesiredMonthlyEarningsSeekBar;

    @BindView(R.id.tv_desired_monthly_income)
    EditText uiDesiredMonthlyEarningsTextView;

    @BindView(R.id.tv_desired_monthly_income_min)
    EditText uiDesiredMonthlyEarningsMinTextView;

    @BindView(R.id.tv_desired_monthly_income_max)
    EditText uiDesiredMonthlyEarningsMaxTextView;


    @BindView(R.id.sb_number_of_periods)
    SeekBar uiNumberOfPeriodsSeekBar;

    @BindView(R.id.tv_number_of_periods)
    EditText uiNumberOfPeriodsTextView;

    @BindView(R.id.tv_number_of_periods_min)
    EditText uiNumberOfPeriodsMinTextView;

    @BindView(R.id.tv_number_of_periods_max)
    EditText uiNumberOfPeriodsMaxTextView;


    @BindView(R.id.sb_rate_per_period)
    SeekBar uiRatePerPeriodSeekBar;

    @BindView(R.id.tv_rate_per_period)
    EditText uiRatePerPeriodTextView;

    @BindView(R.id.tv_rate_per_period_min)
    EditText uiRatePerPeriodMinTextView;

    @BindView(R.id.tv_rate_per_period_max)
    EditText uiRatePerPeriodMaxTextView;

    @BindView(R.id.tv_current_value)
    EditText uiCurrentValue;

    @BindView(R.id.savings_per_period)
    TextView uiSavingsPerPeriodTextView;

    NumberFormat nf_out = NumberFormat.getNumberInstance(Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((IncomeApplication) getApplication()).component().inject(this);


        setSeekBarListeners();
    }

    private void setSeekBarListeners() {
        setDesiredEarningSeekBarListener();
        setNumberOfPeriodsSeekBarListener();
        setRatePerPeriodSeekBarListener();
    }

    private void setDesiredEarningSeekBarListener() {
        uiDesiredMonthlyEarningsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    presenter.onUserChangedDesiredEarningsSeekBar(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not Used
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // not used
            }
        });
    }

    private void setNumberOfPeriodsSeekBarListener() {
        uiNumberOfPeriodsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    presenter.onUserChangedNumberOfPeriodsSeekBar(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not Used
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // not used
            }
        });
    }

    private void setRatePerPeriodSeekBarListener() {
        uiRatePerPeriodSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    presenter.onUserChangedRatePerPeriodSeekBar(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not Used
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // not used
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResume(this);
        presenter.setInitialValues();
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
    public void onDesiredEarningsChanged(double desiredEarningsMin, double desiredEarnings, double desiredEarningsMax) {
        uiDesiredMonthlyEarningsMinTextView.setText(formatNumber(desiredEarningsMin));
        uiDesiredMonthlyEarningsMaxTextView.setText(formatNumber(desiredEarningsMax));
        uiDesiredMonthlyEarningsTextView.setText(formatNumber(desiredEarnings));
        uiDesiredMonthlyEarningsTextView.clearFocus();
        double position = getSeekBarPosition(desiredEarningsMin, desiredEarnings, desiredEarningsMax);
        uiDesiredMonthlyEarningsSeekBar.setProgress((int) position);
    }

    @Override
    public void onNumberOfPeriodsChanged(int numberOfPeriodsMin, int numberOfPeriods, int numberOfPeriodsMax) {
        uiNumberOfPeriodsMinTextView.setText(String.valueOf(numberOfPeriodsMin));
        uiNumberOfPeriodsMaxTextView.setText(String.valueOf(numberOfPeriodsMax));
        uiNumberOfPeriodsTextView.setText(String.valueOf(numberOfPeriods));
        double position = getSeekBarPosition(numberOfPeriodsMin, numberOfPeriods, numberOfPeriodsMax);
        uiNumberOfPeriodsSeekBar.setProgress((int) position);
    }

    @Override
    public void onRatePerPeriodChanged(double ratePerPeriodMin, double ratePerPeriod, double ratePerPeriodMax) {
        uiRatePerPeriodMinTextView.setText(formatNumber(ratePerPeriodMin));
        uiRatePerPeriodMaxTextView.setText(formatNumber(ratePerPeriodMax));
        uiRatePerPeriodTextView.setText(formatNumber(ratePerPeriod));
        double position = getSeekBarPosition(ratePerPeriodMin, ratePerPeriod, ratePerPeriodMax);
        uiRatePerPeriodSeekBar.setProgress((int) position);
    }

    @Override
    public void onCurrentValueChanged(double currentValue) {
        uiCurrentValue.setText(formatNumber(currentValue));
    }

    @Override
    public void onSavingsPerPeriodChanged(double value) {
        uiSavingsPerPeriodTextView.setText(formatNumber(value));
    }

    private double getSeekBarPosition(double min, double actual, double man) {
        return (actual - min) / (man - min) * 100;
    }

    private String formatNumber(double value) {
        nf_out.setMinimumFractionDigits(2);
        nf_out.setMaximumFractionDigits(2);
        return nf_out.format(value);
    }
}
