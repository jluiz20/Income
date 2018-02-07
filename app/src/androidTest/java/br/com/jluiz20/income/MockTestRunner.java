package br.com.jluiz20.income;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * This Runner class replaces the regular {@link IncomeApplication} class for the
 * {@link MockIncomeApplication} class, in order to enable mocking on android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class MockTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockIncomeApplication.class.getName(), context);
    }
}
