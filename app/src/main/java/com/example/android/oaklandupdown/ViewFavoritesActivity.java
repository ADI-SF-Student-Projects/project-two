package com.example.android.oaklandupdown;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewFavoritesActivity extends AppCompatActivity {

    public static final String MAIN_QUERY = "mainQuery";
    private int isFavorite;
    private ListView favoritesListView;
    private OaklandSQLiteOpenHelper mHelper;
    private Cursor cursor;
    private CursorAdapter simpleCursorAdapter;
    public Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        favoritesListView = (ListView)findViewById(R.id.favorites_listView);
        mHelper = OaklandSQLiteOpenHelper.getInstance(ViewFavoritesActivity.this);

        cursor = mHelper.getPlacesList();
        simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{OaklandSQLiteOpenHelper.COL_NAME},new int[]{android.R.id.text1},0);
        favoritesListView.setAdapter(simpleCursorAdapter);

        handleIntentAndSearch(getIntent());

        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detailIntent = new Intent(ViewFavoritesActivity.this, DetailActivity.class);

                cursor.moveToPosition(position);

                detailIntent.putExtra("id", cursor.getInt(cursor.getColumnIndex(OaklandSQLiteOpenHelper.COL_ID)));

                startActivity(detailIntent);
            }
        });
    }

    private void handleIntentAndSearch(Intent intent) {
        setTitle("Favorite Places");

        cursor = OaklandSQLiteOpenHelper.getInstance(ViewFavoritesActivity.this).searchDBforFavorites(1);
        simpleCursorAdapter.swapCursor(cursor);
    }
}