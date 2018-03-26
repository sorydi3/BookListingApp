package com.example.ibrah.booklistingapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by ibrah on 16/07/2017.
 */

public class LoaderBooks extends AsyncTaskLoader {

    String BOOK_URL;

    public LoaderBooks(Context context, String url) {
        super(context);
        this.BOOK_URL = url;

    }

    @Override
    public List<Book> loadInBackground() {
        if (BOOK_URL == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.+

        List<Book> earthquakes = QuerryBooks.fetchEarthquakeData(BOOK_URL);
        return earthquakes;


    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
