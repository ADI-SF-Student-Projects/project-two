package com.example.android.oaklandupdown;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    /**
     * Created by Stewart McMillan(@stewartmcm) on 3/25/16
     */

    public static String PLACES_LIST_TABLE_NAME;
    //region private variable declarations
    private String name;
    private String phone;
    private int id;
    private String address;
    private String price;
    private int updatedIsFavorite;
    private String description;
    private int placeImage;
    private ImageView placeImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView priceTextView;
    private TextView descriptionTextView;
    private FloatingActionButton isFavoriteButton;
    private OaklandSQLiteOpenHelper mHelper;
    private SQLiteDatabase db;
    private Place currentPlace;

    //endregion private variable declarations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getIntExtra("id", -1);
        mHelper = OaklandSQLiteOpenHelper.getInstance(DetailActivity.this);
        db = mHelper.getReadableDatabase();
        currentPlace = mHelper.getPlace(id);

        PLACES_LIST_TABLE_NAME = "PLACES_LIST";

        setFavoriteButton();
        setFavoriteClickListener();
        setDetails();
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
                Intent favoriteIntent = new Intent(this, ViewFavoritesActivity.class);
                startActivity(favoriteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDetails() {
        if (id >= 0) {

            name = mHelper.getNameById(id);
            nameTextView = (TextView) findViewById(R.id.location_name_textView);
            nameTextView.setText(name);
            setTitle(name);

            address = mHelper.getAddressById(id);
            addressTextView = (TextView) findViewById(R.id.address_textView);
            addressTextView.setText(address);

            phone = mHelper.getPhoneById(id);
            phoneTextView = (TextView) findViewById(R.id.phone_number_textView);
            phoneTextView.setText(phone);

            price = mHelper.getPriceById(id);
            priceTextView = (TextView) findViewById(R.id.price_textView);
            priceTextView.setText(price);

            description = mHelper.getDescriptionById(id);
            descriptionTextView = (TextView) findViewById(R.id.description_textView);
            descriptionTextView.setText(description);

            placeImage = mHelper.getPhotoById(id);
            placeImageView = (ImageView) findViewById(R.id.place_ImageView);
            placeImageView.setImageResource(placeImage);
        }
    }

    /**
     * Method below updates a specific row(Place) in database to reflect that a user has changed
     * the isFavorite value for that particular place.
     *
     * @param rowId
     * @param updatedIsFavorite
     */

    public void updateRow(int rowId, int updatedIsFavorite) {

        ContentValues args = new ContentValues();
        args.put("ISFAVORITE", updatedIsFavorite);
        db.update(PLACES_LIST_TABLE_NAME, args, "_id=" + rowId, null);
    }

    private void setFavoriteButton() {
        isFavoriteButton = (FloatingActionButton) findViewById(R.id.isFavorite_button);

        if (currentPlace.isFavorite == 0) {
            isFavoriteButton.setImageResource(android.R.drawable.star_big_off);
        } else {
            isFavoriteButton.setImageResource(android.R.drawable.star_big_on);
        }
    }

    private void setFavoriteClickListener() {
        isFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentPlace.isFavorite == 0) {

                    updateIsFavorite(1, android.R.drawable.star_big_on);

//                    currentPlace.setIsFavorite(1);
//                    updatedIsFavorite = currentPlace.getIsFavorite();
//                    isFavoriteButton.setImageResource(android.R.drawable.star_big_on);
//                    updateRow(id,updatedIsFavorite);

                } else {

                    updateIsFavorite(0, android.R.drawable.star_big_off);

//                    currentPlace.setIsFavorite(0);
//                    updatedIsFavorite = currentPlace.getIsFavorite();
//                    isFavoriteButton.setImageResource(android.R.drawable.star_big_off);
//                    updateRow(id,updatedIsFavorite);
                }
            }
        });
    }

    private void updateIsFavorite(int isFavorite, int buttonDrawable) {

        currentPlace.setIsFavorite(isFavorite);
        updatedIsFavorite = currentPlace.getIsFavorite();
        isFavoriteButton.setImageResource(buttonDrawable);
        updateRow(id, updatedIsFavorite);

    }

}
