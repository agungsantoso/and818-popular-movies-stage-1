package com.agungsantoso.popularmovies.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by agung.santoso on 6/27/2017.
 */

public class SystemUtils {
    /**
     * Checks if there is accesible internet.
     *
     * @return True if there is Internet. False if not.
     */
    public static boolean networkOf(AppCompatActivity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
