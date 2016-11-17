package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Amanda on 11/11/2016.
 */
public class ViewWorkoutList extends AppCompatActivity {

    private WorkoutExerciseDataSource workexedatasource;
    private String weekDay ;
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

        if(weekDay == ""){
            weekDay = "Monday";
        }

        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        descriptText = (TextView) findViewById(R.id.description);
        weekdayText.setText(weekDay);
        descriptText.setText(workouts.get(0).getWorkoutDescription());

        listView = (ListView) findViewById(R.id.exerciseList);
        listAdapter = new ViewExerciseListAdapter(this, workouts);
        listView.setAdapter(listAdapter);


    } // closes onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lab,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_workout:
                Intent intent = new Intent(this, ViewWorkoutList.class);
                startActivity(intent);
                return true;
            case R.id.menu_exercises:
                Intent intent2 = new Intent(this, ViewExercises.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



} // closes ViewWorkoutList class
