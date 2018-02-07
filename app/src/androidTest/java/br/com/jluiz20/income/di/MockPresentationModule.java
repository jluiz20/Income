package br.com.jluiz20.income.di;

import javax.inject.Singleton;

import br.com.jluiz20.income.presentation.main.MainActivityContract;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * This module in general should only return {@link Singleton} and Mock instances.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Module
public class MockPresentationModule {

    @Provides
    @Singleton
    MainActivityContract.Presenter providesPresenter() {
        return mock(MainActivityContract.Presenter.class);
    }


}
