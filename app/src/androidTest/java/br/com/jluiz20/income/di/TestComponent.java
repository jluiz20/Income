package br.com.jluiz20.income.di;


import javax.inject.Singleton;

import br.com.jluiz20.income.MockIncomeApplication;
import br.com.jluiz20.income.presentation.main.MainActivityTest;
import dagger.Component;

/**
 * Test component that uses a {@link MockIncomeApplication} to enable mocks on
 * android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Singleton
@Component(modules = {MockPresentationModule.class, RepositoryModule.class, AppModule.class})
public interface TestComponent extends AppComponent {

    void inject(MainActivityTest mainActivityTest);

}
