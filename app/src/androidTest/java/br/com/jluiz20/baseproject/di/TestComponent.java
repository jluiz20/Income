package br.com.jluiz20.baseproject.di;


import javax.inject.Singleton;

import br.com.jluiz20.baseproject.MockBaseProjectApplication;
import br.com.jluiz20.baseproject.presentation.main.MainActivityTest;
import dagger.Component;

/**
 * Test component that uses a {@link MockBaseProjectApplication} to enable mocks on
 * android instrumented tests.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Singleton
@Component(modules = {MockPresentationModule.class, RepositoryModule.class, AppModule.class})
public interface TestComponent extends AppComponent {

    void inject(MainActivityTest mainActivityTest);

}
