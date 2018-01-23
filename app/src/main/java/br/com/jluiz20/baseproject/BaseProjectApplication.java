package br.com.jluiz20.baseproject;

import android.app.Application;

import br.com.jluiz20.baseproject.di.AppComponent;
import br.com.jluiz20.baseproject.di.AppModule;
import br.com.jluiz20.baseproject.di.DaggerAppComponent;
import br.com.jluiz20.baseproject.di.DataSourceModule;
import br.com.jluiz20.baseproject.di.PresentationModule;
import br.com.jluiz20.baseproject.di.RepositoryModule;

/**
 * This Android Application is responsible for keep state of the app and general configurations,
 * such as hockey app setup, dependency injection and so on.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class BaseProjectApplication extends Application {

    private AppComponent appComponent = createComponent();

    /**
     * Keep this method separated to enable override on android instrumented tests package.
     */
    AppComponent createComponent() {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataSourceModule(new DataSourceModule())
                .presentationModule(new PresentationModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
