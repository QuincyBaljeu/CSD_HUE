package com.example.csd_hue.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;
    private final String TAG = "DatabaseHandler";

    public DatabaseHandler(Context context){
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_Lamp_Table = "CREATE TABLE " + Constants.TABLE_NAME
                + " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.KEY_HUE + " TEXT, "
                + Constants.KEY_BRIGHTNESS + " TEXT, "
                + Constants.KEY_SATURATION + " TEXT)";

        db.execSQL(Create_Lamp_Table);

        Log.i(TAG, "onCreate" + Create_Lamp_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long addLamp(Lamp lamp){
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_LAMPID, lamp.getLampID());
        values.put(Constants.KEY_HUE, lamp.getLampHue());
        values.put(Constants.KEY_BRIGHTNESS, lamp.getLampBrightness());
        values.put(Constants.KEY_SATURATION, lamp.getLampSaturation());

        SQLiteDatabase db = this.getWritableDatabase();
        long row = db.insert(Constants.TABLE_NAME, null, values);
        Log.d(TAG, "added lamp with id:" + lamp.getLampID() + "at row" + row);
        return row;
    }

}
