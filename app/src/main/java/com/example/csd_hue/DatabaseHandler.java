package com.example.csd_hue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;
    private final String TAG = "DatabaseHandler";

    public DatabaseHandler(Context context){
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
        String Create_Lamp_Table = "CREATE TABLE " + Constants.TABLE_NAME
                + " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.KEY_HUE + " TEXT, "
                +
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
