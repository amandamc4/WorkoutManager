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


    private static final String DATABASE_NAME = "workoutdb.db";
    private static final int DATABASE_VERSION = 1;

    //EXERCISE TABLE
    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_EXEID = "_id";
    public static final String COLUMN_EXENAME = "exercise_name";
    public static final String COLUMN_EXEDESCRIPTION= "exercise_description";

    //WORKOUT TABLE
    public static final String TABLE_WORKOUT = "workouts";
    public static final String COLUMN_WORKOUTID = "_id";
    public static final String COLUMN_DAYOFWEEK = "day_week";
    public static final String COLUMN_WORKOUTDESCRIPTION = "workout_description";
    public static final String COLUMN_CREATEDATE = "create_date";

    //TABLE WORKOUTEXERXISE
    public static final String TABLE_WORKOUTEXERCISE = "workoutexercise";
    public static final String COLUMN_EXERID = "_exercise_id";
    public static final String COLUMN_WORKID = "_workout_id";
    public static final String COLUMN_WORKOUTEXERCISEID = "_id";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_WEIGHT = "weight";



    //CREATE TABLE EXERCISE STATEMENT
    private static final String DATABASE_CREATE_EXERCISE = "CREATE TABLE "
            + TABLE_EXERCISES + "( " + COLUMN_EXEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXENAME + " TEXT NOT NULL, "
            + COLUMN_EXEDESCRIPTION + " TEXT " + " );";

    //CREATE TABLE WORKOUT STATEMENT
    private static final String DATABASE_CREATE_WORKOUT = "CREATE TABLE "
            + TABLE_WORKOUT + "( " + COLUMN_WORKOUTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DAYOFWEEK + " TEXT NOT NULL, "
            + COLUMN_WORKOUTDESCRIPTION + " TEXT, "
            + COLUMN_CREATEDATE + " TEXT NOT NULL " + " );";

    //CREATE TABLE WORKOUTEXERCISE STATEMENT
    private static final String DATABASE_CREATE_WORKOUTEXERCISE = "CREATE TABLE "
            + TABLE_WORKOUTEXERCISE + "( " + COLUMN_WORKOUTEXERCISEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXERID + " INTEGER NOT NULL, "
            + COLUMN_WORKID + " INTEGER NOT NULL, "
            + COLUMN_REPS + " TEXT NOT NULL, "
            + COLUMN_WEIGHT + " DOUBLE "
            + COLUMN_EXENAME + " TEXT NOT NULL, "
            + COLUMN_DAYOFWEEK + " TEXT NOT NULL, "
            + COLUMN_WORKOUTDESCRIPTION + " TEXT, "
            + COLUMN_CREATEDATE + " TEXT NOT NULL " + " );";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_EXERCISE);
        database.execSQL(DATABASE_CREATE_WORKOUT);
        database.execSQL(DATABASE_CREATE_WORKOUTEXERCISE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTEXERCISE);
        onCreate(db);
    }

}
