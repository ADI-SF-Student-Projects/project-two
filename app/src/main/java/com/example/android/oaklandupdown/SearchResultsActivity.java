package com.example.android.oaklandupdown;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {

    /**
     * Created by Stewart McMillan(@stewartmcm) on 3/25/16
     */

    // region variable declarations
    public static final String MAIN_QUERY = "mainQuery";
    private String mainQuery;
    private ListView searchResultListView;
    private TextView noResultsTextView;
    private OaklandSQLiteOpenHelper mHelper;
    private Cursor cursor;
    private CursorAdapter simpleCursorAdapter;
    private Bundle extras;
    // endregion variable declarations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchResultListView = (ListView) findViewById(R.id.search_result_listview);
        noResultsTextView = (TextView) findViewById(R.id.no_results_textView);

        mHelper = OaklandSQLiteOpenHelper.getInstance(SearchResultsActivity.this);
        cursor = mHelper.getPlacesList();
        simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{OaklandSQLiteOpenHelper.COL_NAME}, new int[]{android.R.id.text1}, 0);
        searchResultListView.setAdapter(simpleCursorAdapter);

        PlaceDetailsFragment frag = (PlaceDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_frag);

        handleIntentAndSearch(getIntent());
        setClickListener();
        frag.setPlace(2);

    }

    /**
     * Method below handles intents from the SearchResultsActivity's Action Search or the MainActivity's
     * EditText and displays search results for the query regardless of which activity the query came from.
     *
     * @param intent
     */

    private void handleIntentAndSearch(Intent intent) {
        extras = getIntent().getExtras();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            displayResultsFromSearchManager(intent);

        } else {
            displayResultsFromMainSearch();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorites:
                Intent favoriteIntent = new Intent(this, ViewFavoritesActivity.class);
                startActivity(favoriteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayResultsFromMainSearch() {
        if (extras != null) {
            mainQuery = extras.getString(MAIN_QUERY, "???");

            String searchResultsTitle = "Results for '" + mainQuery + "'";
            setTitle(searchResultsTitle);

            cursor = OaklandSQLiteOpenHelper.getInstance(SearchResultsActivity.this).searchPlacesDB(mainQuery);
            simpleCursorAdapter.swapCursor(cursor);
        }
    }

    private void displayResultsFromSearchManager(Intent intent) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        cursor = OaklandSQLiteOpenHelper.getInstance(SearchResultsActivity.this).searchPlacesDB(query);
        simpleCursorAdapter.swapCursor(cursor);

        String searchResultsTitle = "Results for '" + query + "'";
        setTitle(searchResultsTitle);
    }

    private void setClickListener() {

        if (cursor.moveToFirst()) {
            searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent detailIntent = new Intent(SearchResultsActivity.this, DetailActivity.class);
                    cursor.moveToPosition(position);
                    detailIntent.putExtra("id", cursor.getInt(cursor.getColumnIndex(OaklandSQLiteOpenHelper.COL_ID)));
                    startActivity(detailIntent);
                }
            });
        } else {
            noResultsTextView.setText("No results found.");
        }

    }
}