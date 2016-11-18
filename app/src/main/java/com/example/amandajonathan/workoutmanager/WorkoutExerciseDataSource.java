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

public class WorkoutExerciseDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_WORKOUTEXERCISEID,
            MySQLiteHelper.COLUMN_EXERID, MySQLiteHelper.COLUMN_WORKID,
            MySQLiteHelper.COLUMN_REPS, MySQLiteHelper.COLUMN_WEIGHT, MySQLiteHelper.COLUMN_EXENAME,
            MySQLiteHelper.COLUMN_DAYOFWEEK, MySQLiteHelper.COLUMN_WORKOUTDESCRIPTION, MySQLiteHelper.COLUMN_CREATEDATE};

    public WorkoutExerciseDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WorkoutExercise createWorkoutExercise(long exerciseId, long workoutId, String reps, double weight, String exename, String dayWeek, String weekDesc, String date) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_EXERID, exerciseId);
        values.put(MySQLiteHelper.COLUMN_WORKID, workoutId);
        values.put(MySQLiteHelper.COLUMN_REPS, reps);
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        values.put(MySQLiteHelper.COLUMN_EXENAME, exename);
        values.put(MySQLiteHelper.COLUMN_DAYOFWEEK, dayWeek);
        values.put(MySQLiteHelper.COLUMN_WORKOUTDESCRIPTION, weekDesc);
        values.put(MySQLiteHelper.COLUMN_CREATEDATE, date);

        long insertId = database.insert(MySQLiteHelper.TABLE_WORKOUTEXERCISE, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_WORKOUTEXERCISE,
                allColumns, MySQLiteHelper.COLUMN_WORKOUTEXERCISEID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        WorkoutExercise newWorkout = cursorToWorkoutExercise(cursor);
        cursor.close();
        return newWorkout;
    }

    public List<WorkoutExercise> getAllExercisesByDayOfWeek(String dayWeek) {
        List<WorkoutExercise> workouts = new ArrayList<WorkoutExercise>();

        String selectQuery = "SELECT * FROM " + MySQLiteHelper.TABLE_WORKOUTEXERCISE
                + " WHERE " + MySQLiteHelper.COLUMN_DAYOFWEEK
                + " =  '" + dayWeek + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                WorkoutExercise exercise = cursorToWorkoutExercise(cursor);
                workouts.add(exercise);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return workouts;
    }

    public void deleteWorkoutExercise(String week) {
        database.delete(MySQLiteHelper.TABLE_WORKOUTEXERCISE, MySQLiteHelper.COLUMN_DAYOFWEEK
                + " = '" + week + "'", null);
    }

    public void updateWorkoutExercise(String week, String reps, double weight, long exeId) {
        ContentValues newValues = new ContentValues();
        newValues.put(MySQLiteHelper.COLUMN_REPS, reps);
        newValues.put(MySQLiteHelper.COLUMN_WEIGHT, weight);

        database.update(MySQLiteHelper.TABLE_WORKOUTEXERCISE, newValues, "_exercise_id = " + exeId + " AND day_week = '" + week + "'", null);
    }

//    private WorkoutExercise cursorToWorkoutExercise(Cursor cursor) {
//        WorkoutExercise workoutExercise = new WorkoutExercise();
//        workoutExercise.setId(cursor.getLong(0));
//        workoutExercise.setExerciseId(cursor.getInt(1));
//        workoutExercise.setWorkoutId(cursor.getInt(2));
//        workoutExercise.setReps(cursor.getString(3));
//        workoutExercise.setWeight(cursor.getDouble(4));
//        return workoutExercise;
//    }

    public void drop() {

        database.execSQL("DROP TABLE IF EXISTS " + MySQLiteHelper.TABLE_WORKOUTEXERCISE);
    }

    private WorkoutExercise cursorToWorkoutExercise(Cursor cursor) {
        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setId(cursor.getLong(0));
        workoutExercise.setExerciseId(cursor.getInt(1));
        workoutExercise.setWorkoutId(cursor.getInt(2));
        workoutExercise.setReps(cursor.getString(3));
        workoutExercise.setWeight(cursor.getDouble(4));
        workoutExercise.setExerciseName(cursor.getString(5));
        workoutExercise.setWorkoutDay(cursor.getString(6));
        workoutExercise.setWorkoutDescription(cursor.getString(7));
        workoutExercise.setCreateDate(cursor.getString(8));
        return workoutExercise;
    }


}
