package br.com.jluiz20.baseproject;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * This Runner class replaces the regular {@link BaseProjectApplication} class for the
 * {@link MockBaseProjectApplication} class, in order to enable mocking on android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class MockTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockBaseProjectApplication.class.getName(), context);
    }
}
