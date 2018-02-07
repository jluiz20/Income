package br.com.jluiz20.income.di;

import javax.inject.Singleton;

import br.com.jluiz20.income.model.datasource.version.LocalVersionDataSource;
import br.com.jluiz20.income.model.datasource.version.VersionDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Module responsible for generate instances of classes related to the model data.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Module
public class DataSourceModule {

    @Provides
    @Singleton
    VersionDataSource providesVersionDataSource() {
        return new LocalVersionDataSource();
    }


}
