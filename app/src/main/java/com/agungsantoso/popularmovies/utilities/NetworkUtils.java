package com.agungsantoso.popularmovies.utilities;

import android.net.Uri;

import com.agungsantoso.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by agung.santoso on 6/27/2017.
 */

public class NetworkUtils {

    private String apiKey;

    public NetworkUtils(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates and returns an URL.
     *
     * @param parameters Parameters to be used in the API call
     * @return URL formatted with parameters for the API
     * @throws MalformedURLException
     */
    public URL urlFrom(String[] parameters) throws MalformedURLException {
        final String TMDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie?";
        final String SORT_BY_PARAM = "sort_by";
        final String API_KEY_PARAM = "api_key";

        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendQueryParameter(SORT_BY_PARAM, parameters[0])
                .appendQueryParameter(API_KEY_PARAM, this.apiKey)
                .build();

        return new URL(builtUri.toString());
    }

    /**
     * Extracts data from the JSON object.
     *
     * @param moviesJsonStr JSON string to be traversed
     * @return Array of Movie objects
     * @throws JSONException
     */
    public Movie[] arrayFrom(String moviesJsonStr) throws JSONException {
        // JSON tags
        final String TAG_RESULTS = "results";
        final String TAG_ORIGINAL_TITLE = "original_title";
        final String TAG_POSTER_PATH = "poster_path";
        final String TAG_OVERVIEW = "overview";
        final String TAG_VOTE_AVERAGE = "vote_average";
        final String TAG_RELEASE_DATE = "release_date";

        // Get the array containing movies
        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray resultsArray = moviesJson.getJSONArray(TAG_RESULTS);

        // Create array of Movie objects
        Movie[] movies = new Movie[resultsArray.length()];

        // Traverse through movies
        for (int i = 0; i < resultsArray.length(); i++) {
            // Initialize each object
            movies[i] = new Movie();

            JSONObject movieInfo = resultsArray.getJSONObject(i);

            // Store data in movie object
            movies[i].originalTitleAs(movieInfo.getString(TAG_ORIGINAL_TITLE));
            movies[i].posterPathAs(movieInfo.getString(TAG_POSTER_PATH));
            movies[i].overviewAs(movieInfo.getString(TAG_OVERVIEW));
            movies[i].voteAverageAs(movieInfo.getDouble(TAG_VOTE_AVERAGE));
            movies[i].releaseDateAs(movieInfo.getString(TAG_RELEASE_DATE));
        }

        return movies;
    }
}
