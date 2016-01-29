package de.kraussdev.redbutton.rest;

import com.squareup.okhttp.ResponseBody;

import javax.inject.Inject;

import retrofit.Call;

/**
 * Created by pk on 27.01.16.
 */
public class JenkinsClient {
    private final JenkinsEndpointInterface mApiInterface;
    private final String mAuthToken;

    @Inject
    public JenkinsClient(JenkinsEndpointInterface apiInterface,
                         String authToken) {
        mApiInterface = apiInterface;
        mAuthToken = authToken;
    }

    public Call<ResponseBody> triggerBuild() {
        // the empty string is needed to provide the job url dynamically
        return mApiInterface.triggerBuild(mAuthToken, "");
    }
}
