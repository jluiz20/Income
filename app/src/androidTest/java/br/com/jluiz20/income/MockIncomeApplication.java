package br.com.jluiz20.income;


import br.com.jluiz20.income.di.AppModule;
import br.com.jluiz20.income.di.DaggerTestComponent;
import br.com.jluiz20.income.di.MockPresentationModule;
import br.com.jluiz20.income.di.RepositoryModule;
import br.com.jluiz20.income.di.TestComponent;

/**
 * Android application to enable mock on android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class MockIncomeApplication extends IncomeApplication {

    @Override
    public TestComponent createComponent() {
        return DaggerTestComponent.builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule())
                .mockPresentationModule(new MockPresentationModule())
                .build();
    }

}
