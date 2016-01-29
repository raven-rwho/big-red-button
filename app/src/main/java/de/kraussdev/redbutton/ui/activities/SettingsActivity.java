package de.kraussdev.redbutton.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import de.kraussdev.redbutton.ui.fragments.SettingsFragment;

/**
 * Created by pk on 27.01.16.
 */
public class SettingsActivity extends PreferenceActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                            .replace(android.R.id.content, new SettingsFragment())
                            .commit();
    }



}
