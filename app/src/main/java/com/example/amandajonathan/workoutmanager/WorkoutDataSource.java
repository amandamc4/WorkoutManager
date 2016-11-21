package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 11/9/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_WORKOUTID,
            MySQLiteHelper.COLUMN_DAYOFWEEK, MySQLiteHelper.COLUMN_WORKOUTDESCRIPTION, MySQLiteHelper.COLUMN_CREATEDATE};

    public WorkoutDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Workout createWorkout(String workoutDay, String workoutDescription, String createDate) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_DAYOFWEEK, workoutDay);
        values.put(MySQLiteHelper.COLUMN_WORKOUTDESCRIPTION, workoutDescription);
        values.put(MySQLiteHelper.COLUMN_CREATEDATE, createDate);

        long insertId = database.insert(MySQLiteHelper.TABLE_WORKOUT, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_WORKOUT,
                allColumns, MySQLiteHelper.COLUMN_WORKOUTID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        Workout newWorkout = cursorToWorkout(cursor);
        cursor.close();
        return newWorkout;

    } // create workout

//    public void deleteWorkout(Workout workout) {
//        long id = workout.getId();
//        System.out.println("Comment deleted with id: " + id);
//        database.delete(MySQLiteHelper.TABLE_WORKOUT, MySQLiteHelper.COLUMN_WORKOUTID
//                + " = " + id, null);
//    }

    public void deleteWorkout(String week) {
        database.delete(MySQLiteHelper.TABLE_WORKOUT, MySQLiteHelper.COLUMN_DAYOFWEEK
                + " = '" + week + "'", null);
    }

    public void clearAll() {
        database.delete(MySQLiteHelper.TABLE_WORKOUT, null, null);
    }

    public List<Workout> getAllWorkouts() {
        List<Workout> workouts = new ArrayList<Workout>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_WORKOUT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Workout workout = cursorToWorkout(cursor);
            workouts.add(workout);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return workouts;
    }

    public List<String> getAllWorkoutDays() {
        List<String> workouts = new ArrayList<String>();

        String selectQuery = "SELECT DISTINCT " + MySQLiteHelper.COLUMN_DAYOFWEEK + " FROM " + MySQLiteHelper.TABLE_WORKOUT + ";";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String exercise = cursor.getString(cursor.getColumnIndex("day_week"));
                workouts.add(exercise);
                cursor.moveToNext();
            }
        }
        // make sure to close the cursor
        cursor.close();
        return workouts;
    }

    private Workout cursorToWorkout(Cursor cursor) {
        Workout workouts = new Workout();
        workouts.setId(cursor.getLong(0));
        workouts.setWorkoutDay(cursor.getString(1));
        workouts.setWorkoutDescription(cursor.getString(2));
        workouts.setCreateDate(cursor.getString(3));
        return workouts;
    }


}
