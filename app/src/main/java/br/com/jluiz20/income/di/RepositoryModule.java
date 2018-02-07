package br.com.jluiz20.income.di;

import br.com.jluiz20.income.model.datasource.version.VersionDataSource;
import br.com.jluiz20.income.model.repository.version.VersionRepository;
import br.com.jluiz20.income.model.repository.version.VersionRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * This class provides all the repositories that exists in this application.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
@Module
public class RepositoryModule {

    @Provides
    VersionRepository providesVersionRepository(VersionDataSource versionDataSource) {
        return new VersionRepositoryImpl(versionDataSource);
    }

}
