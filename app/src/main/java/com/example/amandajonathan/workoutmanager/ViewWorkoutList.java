package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Amanda on 11/11/2016.
 */
public class ViewWorkoutList extends Activity {

    private WorkoutExerciseDataSource workexedatasource;
    private String weekDay;
    private ViewExerciseListAdapter listAdapter;
    private ListView listView;
    private TextView weekdayText;
    private TextView descriptText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);

        workexedatasource = new WorkoutExerciseDataSource(this);
        workexedatasource.open();

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday"); //MAYBE CHANGE
        weekdayText = (TextView) findViewById(R.id.dayWeek);

        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        descriptText = (TextView) findViewById(R.id.description);
        weekdayText.setText(weekDay);
        descriptText.setText(workouts.get(0).getWorkoutDescription());

        listView = (ListView) findViewById(R.id.exerciseList);
        listAdapter = new ViewExerciseListAdapter(this, workouts);
        listView.setAdapter(listAdapter);
    } // closes onCreate




} // closes ViewWorkoutList class
