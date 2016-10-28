package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 10/28/2016.
 *
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_EXERCISES = "exercises";
    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXENAME = "exercise_name";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_DAYOFWEEK = "day_week";
    public static final String COLUMN_CREATEDATE = "create_date";


    //CREATE TABLE STATEMENT
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_EXERCISES + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXENAME + " TEXT NOT NULL, "
            + COLUMN_REPS + " TEXT, "
            + COLUMN_WEIGHT + " DOUBLE, "
            + COLUMN_DAYOFWEEK + " TEXT NOT NULL, "
            + COLUMN_CREATEDATE + " DATETIME" + " );";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(db);
    }

}
