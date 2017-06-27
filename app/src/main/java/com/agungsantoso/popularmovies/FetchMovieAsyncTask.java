package com.agungsantoso.popularmovies;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.agungsantoso.popularmovies.data.Movie;
import com.agungsantoso.popularmovies.utilities.NetworkUtils;

/**
 * Created by Agung Santoso
 * <p/>
 * Background loading of data from the Internet.
 */
class FetchMovieAsyncTask extends AsyncTask<String, Void, Movie[]> {
    /**
     * TMDb API key
     */
    private final String mApiKey;

    /**
     * Interface / listener
     */
    private final OnTaskCompleted mListener;

    private final NetworkUtils net;

    /**
     * Constructor
     *
     * @param listener UI listener
     * @param apiKey TMDb API key
     */
    public FetchMovieAsyncTask(OnTaskCompleted listener, String apiKey) {
        super();

        mListener = listener;
        mApiKey = apiKey;
        net = new NetworkUtils(mApiKey);
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Holds data returned from the API
        String moviesJsonStr = null;

        try {
            URL url = net.urlFrom(params);

            // Start connecting to get JSON
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Adds newline at last line.
                builder.append(line).append("\n");
            }

            // If builder has zero length
            if (builder.length() == 0) {
                // Nothing to do.
                return null;
            }

            moviesJsonStr = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Release url connection and buffered reader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            // Get movies data from JSON
            return net.arrayFrom(moviesJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        super.onPostExecute(movies);

        // Notify UI
        mListener.onFetchMoviesTaskCompleted(movies);
    }
}
