package br.com.jluiz20.baseproject.model.datasource.version;

/**
 * This class returns all the information about the build version, version name and so on.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public interface VersionDataSource {

    /**
     * Returns the app version.
     */
    String getAppVersion();
}
