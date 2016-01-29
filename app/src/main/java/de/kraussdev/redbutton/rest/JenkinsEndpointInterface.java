package de.kraussdev.redbutton.rest;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Url;

/**
 * Created by pk on 21.01.16.
 */
public interface JenkinsEndpointInterface {

    // ToDo - needs to be changed for a dynamic endpoint (retrofit 2)
    //@POST("/view/android/job/yana-app-android-test/build")
    @POST
    Call<ResponseBody> triggerBuild(@Header("Authorization") String auth, @Url String empty);

}
