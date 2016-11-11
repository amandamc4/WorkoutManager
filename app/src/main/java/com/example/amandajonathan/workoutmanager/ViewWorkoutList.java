package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

/**
 * Created by Amanda on 11/11/2016.
 */
public class ViewWorkoutList extends Activity {

    private WorkoutExerciseDataSource workexedatasource;
    private String weekDay;
    private ViewExerciseListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise);

        workexedatasource = new WorkoutExerciseDataSource(this);
        workexedatasource.open();

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday"); //MAYBE CHANGE

        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        listAdapter = new ViewExerciseListAdapter(this, workouts);

    } // closes onCreate




} // closes ViewWorkoutList class
