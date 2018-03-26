package com.example.ibrah.booklistingapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private String base = "https://www.googleapis.com/books/v1/volumes?maxResults=10&q=";
    private String finalUrl;
    private EditText searchEditTextView;
    private AdapterBooks adapterBooks;
    private NetworkInfo networkInfo;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Book> Books = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.list);
        final TextView EmptyView = (TextView) findViewById(R.id.not_found);
        listView.setEmptyView(EmptyView);
        adapterBooks = new AdapterBooks(this, Books);
        listView.setAdapter(adapterBooks);
        Button button = (Button) findViewById(R.id.search_button);
        searchEditTextView = (EditText) findViewById(R.id.search_bar);
        connectivityManager = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        final LoaderManager loaderManager = getSupportLoaderManager();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            finalUrl = base + "diallo";
            loaderManager.initLoader(0, null, this);
        } else {
            EmptyView.setText("CONEXTION NOT AVAILABLEQ");
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchEditTextView.getText().toString();
                finalUrl = base + searchTerm;
                ConnectivityManager connectivityManager1 = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo1 = connectivityManager1.getActiveNetworkInfo();
                if (networkInfo1 != null && networkInfo1.isConnectedOrConnecting()) {
                    getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "No internet Conection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        LoaderBooks loaderBooks = new LoaderBooks(this, finalUrl);
        return loaderBooks;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

        // Clear the adapter of previous earthquake data
        adapterBooks.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        if (data != null && !data.isEmpty()) {
            adapterBooks.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        adapterBooks.clear();
    }

}
