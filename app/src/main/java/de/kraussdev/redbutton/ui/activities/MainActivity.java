package de.kraussdev.redbutton.ui.activities;

import com.squareup.okhttp.ResponseBody;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.kraussdev.redbutton.R;
import de.kraussdev.redbutton.RedButton;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mMain;
    private String mUserName;
    private String mAuthToken;
    private String mJenkinsUrl;
    private String mJenkinsJob;
    private String mBtnLabel;
    private TextView mBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSharedPref();
        setupUi();

    }

    private void getSharedPref() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String keyBaseUrl = getString(R.string.jenkins_url);
        String keyJob = getString(R.string.jenkins_job);
        String keyJenkinsName = getString(R.string.jenkins_user_name);
        String keyJenkinsApiToken = getString(R.string.jenkins_api_token);
        String keyBtnLabel = getString(R.string.btn_label_key);

        mJenkinsUrl = prefs.getString(keyBaseUrl,"");
        mJenkinsJob = prefs.getString(keyJob,"");
        mUserName = prefs.getString(keyJenkinsName, "");
        mAuthToken = prefs.getString(keyJenkinsApiToken, "");
        mBtnLabel = prefs.getString(keyBtnLabel,"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUi() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.big_red_btn);
        btn.setOnClickListener(this::showMsg);

        mBtnTextView = (TextView) findViewById(R.id.btn_label);
        mBtnTextView.setText(mBtnLabel);

        mMain = (RelativeLayout) findViewById(R.id.main_layout);
    }

    private void showMsg(View view) {
        Snackbar.make(view, R.string.processing, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        triggerBuild();
    }

    private void triggerBuild() {

        if (checkSettingsNotSet())  {
            Snackbar.make(mMain, R.string.settings_not_set,
                         Snackbar.LENGTH_LONG).show();
            return;
        }

        mBtnTextView.setText(mBtnLabel);

        (((RedButton)getApplication()).getComponent().jenkinsClient().triggerBuild())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(final Response<ResponseBody> response,
                                           final Retrofit retrofit) {
                        Timber.d("callback response");
                        Snackbar.make(mMain, R.string.success, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        Timber.e("callback error");
                        Snackbar.make(mMain, R.string.error_unknown, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
    }

    private boolean checkSettingsNotSet() {
        getSharedPref();

        return mJenkinsUrl.equals("") ||
               mJenkinsJob.equals("") ||
               mUserName.equals("") ||
               mAuthToken.equals("") ||
               mBtnLabel.equals("");
    }

}
