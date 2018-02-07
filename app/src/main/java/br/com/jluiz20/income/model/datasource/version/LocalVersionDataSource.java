package br.com.jluiz20.income.model.datasource.version;


import br.com.jluiz20.income.BuildConfig;

/**
 * This class returns all information related to the current version and build locally.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class LocalVersionDataSource implements VersionDataSource {
    @Override
    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }
}
