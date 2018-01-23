package br.com.jluiz20.baseproject;


import br.com.jluiz20.baseproject.di.AppModule;
import br.com.jluiz20.baseproject.di.DaggerTestComponent;
import br.com.jluiz20.baseproject.di.MockPresentationModule;
import br.com.jluiz20.baseproject.di.RepositoryModule;
import br.com.jluiz20.baseproject.di.TestComponent;

/**
 * Android application to enable mock on android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class MockBaseProjectApplication extends BaseProjectApplication {

    @Override
    public TestComponent createComponent() {
        return DaggerTestComponent.builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule())
                .mockPresentationModule(new MockPresentationModule())
                .build();
    }

}
