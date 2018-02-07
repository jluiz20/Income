package br.com.jluiz20.income.di;


import br.com.jluiz20.income.presentation.main.MainActivityContract;
import br.com.jluiz20.income.presentation.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Creates all dependencies related to presentation layer.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Module
public class PresentationModule {

    @Provides
    @SuppressWarnings("WeakerAccess")
    public MainActivityContract.Presenter providesMainPresenter(MainPresenter presenter) {
        return presenter;
    }

}
