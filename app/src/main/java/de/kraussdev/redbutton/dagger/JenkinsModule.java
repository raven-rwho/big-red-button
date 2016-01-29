package de.kraussdev.redbutton.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.kraussdev.redbutton.R;
import de.kraussdev.redbutton.rest.JenkinsClient;
import de.kraussdev.redbutton.rest.JenkinsEndpointInterface;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import timber.log.Timber;

@Module
public class JenkinsModule {

    Context mContext;
    private SharedPreferences mPrefs;

    public JenkinsModule(Context context) {
        mContext = context;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Provides
    @Singleton
    JenkinsEndpointInterface provideShutterstockApiInterface() {
        Timber.d("url: %s", baseUrl());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(JenkinsEndpointInterface.class);
    }

    private String baseUrl() {
        String buildUrl = mPrefs.getString(mContext.getString(R.string.jenkins_job),"") + mContext.getString(R.string.build);
        return mPrefs.getString(mContext.getString(R.string.jenkins_url), "") + buildUrl;
    }

    @Provides
    @Singleton
    String provideAuthToken() {
        String uName = mPrefs.getString(mContext.getString(R.string.jenkins_user_name), "");
        String apiToken = mPrefs.getString(mContext.getString(R.string.jenkins_api_token), "");

        String token = uName + ":" +
                       apiToken;
        Timber.d("username: %s", token);
        return "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
    }

    @Provides
    @Singleton
    JenkinsClient provideShutterstockClient(JenkinsEndpointInterface jenkinsEndpointInterface,
                                                 String authToken) {
        return new JenkinsClient(jenkinsEndpointInterface, authToken);
    }

}
