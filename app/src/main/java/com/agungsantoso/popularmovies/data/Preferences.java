package com.agungsantoso.popularmovies.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.agungsantoso.popularmovies.R;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by agung.santoso on 6/27/2017.
 */

public class Preferences {

    AppCompatActivity activity;

    public Preferences(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**

     * Gets the sort method set by user.
     * Default to sorting by popularity.
     *
     * @return Sort method from SharedPreferenced
     */
    public String sortMethod() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);

        return prefs.getString(activity.getString(R.string.pref_sort_method_key),
                activity.getString(R.string.tmdb_sort_pop_desc));
    }

    /**
     * Saves the selected sort method
     *
     * @param sortMethod Sort method to save
     */
    public void updateSharedPrefs(String sortMethod) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(activity.getString(R.string.pref_sort_method_key), sortMethod);
        editor.apply();
    }
}
