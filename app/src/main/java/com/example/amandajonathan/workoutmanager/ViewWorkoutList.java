package com.example.amandajonathan.workoutmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Amanda on 11/11/2016.
 */
public class ViewWorkoutList extends AppCompatActivity {

    private WorkoutExerciseDataSource workexedatasource;
    private WorkoutDataSource workoutdatasource;
    private String weekDay ;
    private ViewExerciseListAdapter listAdapter;
    private ListView listView;
    private TextView weekdayText;
    private TextView descriptText;
    List<String> daysAvailable;
    int delete = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);

        workexedatasource = new WorkoutExerciseDataSource(this);
        workexedatasource.open();

        workoutdatasource = new WorkoutDataSource(this);
        workoutdatasource.open();


        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday"); //MAYBE CHANGE
        weekdayText = (TextView) findViewById(R.id.dayWeek);

        daysAvailable = workoutdatasource.getAllWorkoutDays();

        if(weekDay == ""){
            weekDay = daysAvailable.get(0);
        }

        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        descriptText = (TextView) findViewById(R.id.description);
        weekdayText.setText(weekDay);
        descriptText.setText(workouts.get(0).getWorkoutDescription());

        listView = (ListView) findViewById(R.id.exerciseList);
        listAdapter = new ViewExerciseListAdapter(this, workouts);
        listView.setAdapter(listAdapter);

    } // closes onCreate

    public void updateActivity(){
        weekDay = daysAvailable.get(0);
        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        descriptText = (TextView) findViewById(R.id.description);
        weekdayText.setText(weekDay);
        descriptText.setText(workouts.get(0).getWorkoutDescription());

        listView = (ListView) findViewById(R.id.exerciseList);
        listAdapter = new ViewExerciseListAdapter(this, workouts);
        listView.setAdapter(listAdapter);
    }


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

    public void deleteWorkout(View view) {

        AlertDialog alertDialog = new AlertDialog.Builder(ViewWorkoutList.this).create();
        alertDialog.setTitle("Delete this Workout");
        alertDialog.setMessage("Are you sure you want to delete the " + weekDay + " workout?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        workoutdatasource.deleteWorkout(weekDay);
                        workexedatasource.deleteWorkoutExercise(weekDay);
                        updateActivity();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void editWorkout(View view) {
        Intent editIntent = new Intent("com.example.amandajonathan.workoutmanager.EditWorkout");
        editIntent.putExtra("weekday", weekDay);
        editIntent.putExtra("comingFromView", "yes");
        startActivity(editIntent);
    }



} // closes ViewWorkoutList class
