package de.kraussdev.redbutton;

import android.app.Application;

import de.kraussdev.redbutton.dagger.AppModule;
import de.kraussdev.redbutton.dagger.DaggerRedButtonComponent;
import de.kraussdev.redbutton.dagger.RedButtonComponent;
import de.kraussdev.redbutton.dagger.JenkinsModule;
import timber.log.Timber;

public class RedButton extends Application {

    // private RestClient mRestClient;
    private RedButtonComponent mRedButtonComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Timber.d("Application inititalized");

        // needs to become a module
        //mRestClient = new RestClient();

        mRedButtonComponent = DaggerRedButtonComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .jenkinsModule(new JenkinsModule(getApplicationContext()))
                .build();

    }

//    public RestClient getRestClient() {
//        return mRestClient;
//    }
//
//    public void setRestClient(RestClient restClient) {
//        this.mRestClient = restClient;
//    }

    public RedButtonComponent getComponent() {
        return mRedButtonComponent;
    }

}
