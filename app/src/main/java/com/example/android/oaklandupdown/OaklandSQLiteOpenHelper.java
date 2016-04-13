package com.example.android.oaklandupdown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OaklandSQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Created by stewartmcmillan on 3/25/16.
     */

    public static final String PLACES_LIST_TABLE_NAME = "PLACES_LIST";

    //region Public constants
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "NAME";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_SUBTYPE = "SUBTYPE";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_ISFAVORITE = "ISFAVORITE";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PHOTO = "PHOTO";
    //endregion Public constants

    private static OaklandSQLiteOpenHelper instance;

    private static final String DATABASE_NAME = "UPTOWN_DOWNTOWN_GUIDE_DB";
    private static final int DATABASE_VERSION = 7;

    private static final String[] PLACES_COLUMNS = {COL_ID, COL_NAME, COL_TYPE, COL_SUBTYPE,
            COL_ADDRESS, COL_PHONE, COL_PRICE, COL_ISFAVORITE,
            COL_DESCRIPTION, COL_PHOTO};

    private static final String CREATE_PLACES_LIST_TABLE =
            "CREATE TABLE " + PLACES_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT, " +
                    COL_TYPE + " TEXT, " +
                    COL_SUBTYPE + " TEXT, " +
                    COL_ADDRESS + " TEXT, " +
                    COL_PHONE + " TEXT, " +
                    COL_PRICE + " TEXT, " +
                    COL_ISFAVORITE + " TEXT, " +
                    COL_DESCRIPTION + " TEXT, " +
                    COL_PHOTO + " TEXT )";

    public static OaklandSQLiteOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OaklandSQLiteOpenHelper(context);
        }
        return instance;
    }

    public OaklandSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLACES_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLACES_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(int id, String name, String type, String subType, String address, String phone,
                       String price, int isFavorite, String description, int photo) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NAME, name);
        values.put(COL_TYPE, type);
        values.put(COL_SUBTYPE, subType);
        values.put(COL_ADDRESS, address);
        values.put(COL_PHONE, phone);
        values.put(COL_PRICE, price);
        values.put(COL_ISFAVORITE, isFavorite);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_PHOTO, photo);

        db.insert(PLACES_LIST_TABLE_NAME, null, values);
    }

    public Cursor getPlacesList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                PLACES_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

    public String getNameById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_NAME},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_NAME));
        } else {
            return "No type found";
        }
    }

    public String getTypeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_TYPE},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_TYPE));
        } else {
            return "No Type found.";
        }
    }

    public String getSubTypeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_SUBTYPE},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_SUBTYPE));
        } else {
            return "No SubType found.";
        }
    }

    public String getAddressById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_ADDRESS},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
        } else {
            return "No address found.";
        }
    }

    public String getPhoneById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_PHONE},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_PHONE));
        } else {
            return "No type found";
        }
    }

    public String getPriceById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_PRICE},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_PRICE));
        } else {
            return "No price found.";
        }
    }

    public int getIsFavoriteById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_ISFAVORITE},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(COL_ISFAVORITE));
        } else {
            return 99;
        }
    }

    public String getDescriptionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_DESCRIPTION},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
        } else {
            return "No description found.";
        }
    }

    public int getPhotoById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                new String[]{COL_PHOTO},
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(COL_PHOTO));
        } else {
            return 99;
        }
    }

    public int deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int deleteNum = db.delete(PLACES_LIST_TABLE_NAME,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return deleteNum;
    }

    public Cursor searchPlacesDB(String query) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                PLACES_COLUMNS, // b. column names
                COL_NAME + " LIKE" + "'%" + query + "%' OR " + COL_TYPE + " LIKE" + "'%"
                        + query + "%' OR " + COL_SUBTYPE + " LIKE" + "'%"
                        + query + "%' OR " + COL_PRICE + " LIKE" + "'%" + query + "%'", // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;

    }

    public Cursor searchDBforFavorites(int integer) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, // a. table
                PLACES_COLUMNS, // b. column names
                COL_ISFAVORITE + " LIKE" + "'%" + 1 + "%'", // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;

    }

    public Place getPlace(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = new String[]{
                COL_ID,
                COL_NAME,
                COL_TYPE,
                COL_SUBTYPE,
                COL_ADDRESS,
                COL_PHONE,
                COL_PRICE,
                COL_ISFAVORITE,
                COL_DESCRIPTION,
                COL_PHOTO};

        String selection = COL_ID + " = ?";

        String[] selectionArgs = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(PLACES_LIST_TABLE_NAME, projection, selection, selectionArgs,
                null, null, null, null);

        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
        String subType = cursor.getString(cursor.getColumnIndex(COL_SUBTYPE));
        String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
        String phone = cursor.getString(cursor.getColumnIndex(COL_PHONE));
        String price = cursor.getString(cursor.getColumnIndex(COL_PRICE));
        int isFavorite = cursor.getInt(cursor.getColumnIndex(COL_ISFAVORITE));
        String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
        int photo = cursor.getInt(cursor.getColumnIndex(COL_PHOTO));

        return new Place(id, name, type, subType, address, phone, price, isFavorite,
                description, photo);
    }
}
