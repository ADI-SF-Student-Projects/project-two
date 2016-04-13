package com.example.android.oaklandupdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Created by Stewart McMillan(@stewartmcm) on 3/25/16
     */

    //region variable declarations
    private EditText searchEditText;
    private FloatingActionButton fab;
    public OaklandSQLiteOpenHelper oaklandSQLiteOpenHelper;
    public ArrayList<Place> placesArrayList;
    public String mainQuery;
    public ArrayList<Place> favoritePlacesArrayList;
    //endregion variable declarations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDB();
        initFavorites();

        searchEditText = (EditText)findViewById(R.id.search_editText);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchResultsActivity();
                searchEditText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorites:
                Intent favoriteIntent = new Intent(this,ViewFavoritesActivity.class);
                startActivity(favoriteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Method below creates new Place objects and adds them to database of Oakland locations.
     */

    private void initDB() {

        oaklandSQLiteOpenHelper = OaklandSQLiteOpenHelper.getInstance(this);
        placesArrayList = new ArrayList<>();

        Place cosecha = new Place(0, "Cosecha",getString(R.string.restaurant_type),getString(R.string.mexican_subtype),getString(R.string.cosecha_address), "(510) 452-5900",getString(R.string.$$_price),0,getString(R.string.cosecha_description),R.drawable.cosecha);
        Place hopscotch = new Place(1, "Hopscotch",getString(R.string.restaurant_type),getString(R.string.american_subtype),getString(R.string.hopscotch_address), "(510) 788-6217",getString(R.string.$$$_price), 0,getString(R.string.hopscotch_description), R.drawable.hopscotch);
        Place cafeVanCleef = new Place(2, "Cafe Van Kleef",getString(R.string.bar_type),getString(R.string.dive_subtype),getString(R.string.cvk_address), "(510) 763-7711",getString(R.string.$$_price), 0,getString(R.string.cvk_description), R.drawable.cafe_van_kleef);
        Place foxTheater = new Place(3, "Fox Theater",getString(R.string.music_venue_type),getString(R.string.concert_theatre_subtype),getString(R.string.fox_address),"(510) 302-2250",getString(R.string.$$$_price), 0,getString(R.string.fox_description), R.drawable.foxtheater);
        Place newParkwayTheater = new Place(4, "New Parkway Theater",getString(R.string.movie_theatre_type),getString(R.string.second_run_subtype),getString(R.string.new_parkway_address), "(510) 658-7900",getString(R.string.$_price), 0,getString(R.string.new_parkway_description), R.drawable.new_parkway);
        Place cathedralBuilding = new Place(5, "Cathedral Building",getString(R.string.sightseeing_type),getString(R.string.cathedral_building_address),getString(R.string.cathedral_building_address), "(xxx) xxx-xxxx",getString(R.string.$_price), 0,getString(R.string.cathedral_building_description), R.drawable.cathedral_building);
        Place greatWesternPowerCo = new Place(6, "Great Western Power Company",getString(R.string.things_to_do_type),getString(R.string.climbing_gym_subtype),getString(R.string.gwpc_address), "(510) 452-2022",getString(R.string.$$_price), 0,getString(R.string.gwpc_description), R.drawable.great_western_power_supply);
        Place bellanico = new Place(7,"Bellanico",getString(R.string.restaurant_type),getString(R.string.italian_subtype),getString(R.string.bellanico_address),"(510) 336-1180",getString(R.string.$$_price),0,getString(R.string.bellanico_description),R.drawable.bellanico);
        Place oaklandIceCenter = new Place(8,"Oakland Ice Center",getString(R.string.things_to_do_type),getString(R.string.ice_arena_subtype),getString(R.string.ice_arena_address),"(510) 268-9000",getString(R.string.$_price),0,getString(R.string.ice_arena_description), R.drawable.ice_arena);
        Place theTrappist = new Place(9,"The Trappist",getString(R.string.bar_type),getString(R.string.belgian_subtype),getString(R.string.trappist_address),"(510) 238-8900",getString(R.string.$_price),0,getString(R.string.trappist_description), R.drawable.the_trappist);

        placesArrayList.add(cosecha);
        placesArrayList.add(hopscotch);
        placesArrayList.add(cafeVanCleef);
        placesArrayList.add(foxTheater);
        placesArrayList.add(newParkwayTheater);
        placesArrayList.add(cathedralBuilding);
        placesArrayList.add(greatWesternPowerCo);
        placesArrayList.add(bellanico);
        placesArrayList.add(oaklandIceCenter);
        placesArrayList.add(theTrappist);

        insertAllPlacesIntoDB();
    }

    private void insertAllPlacesIntoDB() {

        for(final Place place : placesArrayList) {

            oaklandSQLiteOpenHelper.insert(
                    place.getId(),
                    place.getName(),
                    place.getType(),
                    place.getSubType(),
                    place.getAddress(),
                    place.getPhone(),
                    place.getPrice(),
                    place.getIsFavorite(),
                    place.getDescription(),
                    place.getPhoto());
        }
    }

    private void startSearchResultsActivity() {

        mainQuery = searchEditText.getText().toString();

        if(mainQuery.isEmpty()) {
            Toast.makeText(MainActivity.this, "Oops. You didn't search for anything.", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(this, SearchResultsActivity.class);
            intent.putExtra(SearchResultsActivity.MAIN_QUERY, mainQuery);
            startActivity(intent);
        }
    }

    /**
     * Method below adds places that have been favorited to an array list of favorites.
     */

    private void initFavorites() {
        favoritePlacesArrayList = new ArrayList<>();

        for(Place place : placesArrayList) {
            if (place.isFavorite == 1) {
                favoritePlacesArrayList.add(place);
            }
        }
    }
}
