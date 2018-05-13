package com.articlegure.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION =1 ;

    // Database Name
    private static final String DATABASE_NAME = "articlegure_data";

    // Login table name
    private static final String TABLE_ARTICLE = "article";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LIKES = "likes";



    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLE_TABLE = "CREATE TABLE " + TABLE_ARTICLE + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UID + " TEXT,"
        + KEY_IMAGE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_LIKES + " TEXT" + ")";
        db.execSQL(CREATE_ARTICLE_TABLE);
        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing article details in database
     */
    public void addArticle(String id,String image, String description, String likes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, id);
        values.put(KEY_IMAGE, image);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_LIKES, likes);

        // Inserting Row
        long uid = db.insert(TABLE_ARTICLE, null, values);

        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + uid);
    }





    public void updateUser(String id,String image, String description, String likes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, image); // Email
        values.put(KEY_DESCRIPTION, description); // Email
        values.put(KEY_LIKES, likes); // Email
        //values.put(KEY_PROFILE_IMAGE, imageURL);//Profile Picture
        String[] args = new String[]{ id};

        long key_id = db.update(TABLE_ARTICLE, values, KEY_ID + " = ?", args);
        db.close();

        Log.d(TAG, "User updated into sqlite: " + key_id);
       // Log.d(TAG, "User update: {name: " + first_name + " ,phone: " + phone + " ,address: " + work_address + " }");
    }





    public ArrayList<HashMap<String, String>> getUserDetails() {
        ArrayList<HashMap<String, String>> user = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_ARTICLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Move to first row
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            HashMap<String, String> product = new HashMap<String, String>();
            // product.put("product_id", cursor.getString(0));
            product.put("uid", cursor.getString(1));
            product.put("images", cursor.getString(2));
            product.put("description", cursor.getString(3));
            product.put("likes", cursor.getString(4));

            user.add(product);
        }

        Log.d(TAG, "Fetching shop count: " + user.size());
        cursor.close();
        db.close();
        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     */

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_ARTICLE, null, null);

        db.close();
        Log.d(TAG, "Deleted all tables info from sqliteDB");
    }

}
