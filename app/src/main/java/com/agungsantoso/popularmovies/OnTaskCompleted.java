package com.agungsantoso.popularmovies;

import com.agungsantoso.popularmovies.data.Movie;

/**
 * Created by Agung Santoso
 * <p/>
 * Based on http://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui
 */
interface OnTaskCompleted {
    void onFetchMoviesTaskCompleted(Movie[] movies);
}
