package br.com.jluiz20.income.presentation.main;


/**
 * This class is an example of a contract for a screen and its presenter.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public interface MainActivityContract {

    /**
     * View part of the contract.
     */
    interface View {

        /**
         * Show the app version.
         */
        void showAppVersion(String appVersion);

        /**
         * updates the desired earnings view
         *
         * @param desiredEarningsMin the min value
         * @param desiredEarnings    actual value
         * @param desiredEarningsMax max value
         */
        void onDesiredEarningsChanged(double desiredEarningsMin, double desiredEarnings, double desiredEarningsMax);

        /**
         * updates the number of periods view
         *
         * @param numberOfPeriodsMin the min value
         * @param numberOfPeriods    actual value
         * @param numberOfPeriodsMax max value
         */
        void onNumberOfPeriodsChanged(int numberOfPeriodsMin, int numberOfPeriods, int numberOfPeriodsMax);


        /**
         * updates the rate per period
         *
         * @param ratePerPeriodMin the min value
         * @param ratePerPeriod    actual value
         * @param ratePerPeriodMax max value
         */
        void onRatePerPeriodChanged(double ratePerPeriodMin, double ratePerPeriod, double ratePerPeriodMax);

        /**
         * the current value was changed
         *
         * @param currentValue the current value
         */
        void onCurrentValueChanged(double currentValue);

        void onSavingsPerPeriodChanged(double value);

    }

    /**
     * Presenter part of the contract.
     */
    interface Presenter {


        /**
         * Called when the view enters on resume state, generally this are loaded here.
         */
        void onViewResume(View view);

        double calculate(double ratePerPeriod, double numberOfPeriods, double initialValue, double desiredEarnings);

        /**
         * Called when then view enters on pause state, usually this is the time to release strong
         * references to an Activity or fragment for example.
         */
        void onViewPause(View view);

        /**
         * set initial values for the fields
         */
        void setInitialValues();

        void onUserChangedDesiredEarningsSeekBar(int progress);

        void onUserChangedNumberOfPeriodsSeekBar(int progress);

        void onUserChangedRatePerPeriodSeekBar(int progress);

    }
}
