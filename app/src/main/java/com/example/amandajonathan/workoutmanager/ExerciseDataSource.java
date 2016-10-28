package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 10/28/2016.
 */


import java.util.ArrayList;

import java.util.List;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExerciseDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_EXENAME, MySQLiteHelper.COLUMN_REPS, MySQLiteHelper.COLUMN_WEIGHT,
            MySQLiteHelper.COLUMN_DAYOFWEEK, MySQLiteHelper.COLUMN_CREATEDATE};

    public ExerciseDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(String exerciseName, String reps, double weight, String weekDay, String createdAt) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_EXENAME, exerciseName);
        values.put(MySQLiteHelper.COLUMN_REPS, reps);
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        values.put(MySQLiteHelper.COLUMN_DAYOFWEEK, weekDay);
        values.put(MySQLiteHelper.COLUMN_CREATEDATE, createdAt);
        long insertId = database.insert(MySQLiteHelper.TABLE_EXERCISES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise exercise) {
        long id = exercise.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_EXERCISES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            exercises.add(exercise);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return exercises;
    }




    private Exercise cursorToExercise(Cursor cursor) {
        Exercise exercises = new Exercise();
        exercises.setId(cursor.getLong(0));
        exercises.setExerciseName(cursor.getString(1));
        exercises.setExerciseReps(cursor.getString(2));
        exercises.setWeight(cursor.getDouble(3));
        exercises.setDayOfWeek(cursor.getString(4));
        exercises.setCreateDate(cursor.getString(5));
        return exercises;
    }

}
