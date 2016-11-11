package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 10/28/2016.
 */


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExerciseDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_EXEID,
            MySQLiteHelper.COLUMN_EXENAME, MySQLiteHelper.COLUMN_EXEDESCRIPTION};

    public ExerciseDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(String exerciseName, String exerciseDescription) {
        ContentValues values = new ContentValues();

        exerciseDescription = exerciseDescription.replaceAll("\\<p>", "");
        exerciseDescription = exerciseDescription.replaceAll("\\</p>", "");

        values.put(MySQLiteHelper.COLUMN_EXENAME, exerciseName);
        values.put(MySQLiteHelper.COLUMN_EXEDESCRIPTION, exerciseDescription);

        long insertId = database.insert(MySQLiteHelper.TABLE_EXERCISES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISES,
                allColumns, MySQLiteHelper.COLUMN_EXEID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise exercise) {
        long id = exercise.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_EXERCISES, MySQLiteHelper.COLUMN_EXEID
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

    public long getExerciseByName(String name) {

        long id = -1;

//        String selectQuery = "SELECT _id FROM " + MySQLiteHelper.TABLE_EXERCISES + " e "
//                + "WHERE e." + MySQLiteHelper.COLUMN_EXENAME.toLowerCase()
//                + " = '" + name.toLowerCase() + "'";

        String selectQuery = "SELECT _id FROM " + MySQLiteHelper.TABLE_EXERCISES
                + " WHERE " + MySQLiteHelper.COLUMN_EXENAME
                + " =  '" + name + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null){
            cursor.moveToFirst();
            id = cursor.getLong(0);
        }


        // make sure to close the cursor

        cursor.close();
        return id;
    }


    private Exercise cursorToExercise(Cursor cursor) {
        Exercise exercises = new Exercise();
        exercises.setId(cursor.getLong(0));
        exercises.setExerciseName(cursor.getString(1));
        exercises.setExerciseDescription(cursor.getString(2));
        return exercises;
    }

}
