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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
    private Spinner dropdown;
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);

        workexedatasource = new WorkoutExerciseDataSource(this);
        workexedatasource.open();

        workoutdatasource = new WorkoutDataSource(this);
        workoutdatasource.open();

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday");
        dropdown = (Spinner) findViewById(R.id.weekSpinner);

        daysAvailable = workoutdatasource.getAllWorkoutDays();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysAvailable);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(weekDay);
        dropdown.setSelection(spinnerPosition, false);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();
                updateActivity(daysAvailable.get(index));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        }); // Closes onItemSelected

        List<WorkoutExercise> workouts;

        workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);


        descriptText = (TextView) findViewById(R.id.description);
        descriptText.setText(workouts.get(0).getWorkoutDescription());

        listView = (ListView) findViewById(R.id.exerciseList);
        listAdapter = new ViewExerciseListAdapter(this, workouts);
        listView.setAdapter(listAdapter);

    } // closes onCreate

    public void updateActivity(String week){
        List<String> daysAvailable2 = workoutdatasource.getAllWorkoutDays();

       if(week.equals("")){
            weekDay = daysAvailable2.get(0);
            adapter.clear();
            adapter.addAll(daysAvailable2);
            adapter.notifyDataSetChanged();
            int spinnerPosition = adapter.getPosition(weekDay);
            dropdown.setSelection(spinnerPosition, false);
        }
        else{
            weekDay = week;
        }
        List<WorkoutExercise> workouts = workexedatasource.getAllExercisesByDayOfWeek(weekDay);

        descriptText = (TextView) findViewById(R.id.description);
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
                updateActivity("");
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
                        int size = workoutdatasource.isTableEmpty();
                        if(size==0){
                            Intent addNewWorkout = new Intent("com.example.amandajonathan.workoutmanager.MainActivity");
                            startActivity(addNewWorkout);
                        }
                        else{
                            updateActivity("");
                        }
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

    public void addNewWorkout(View view) {
        Intent addDayWeek = new Intent("com.example.amandajonathan.workoutmanager.AddDayWeek");
        startActivity( addDayWeek );
    }

    public void clearAll(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(ViewWorkoutList.this).create();
        alertDialog.setTitle("Clear All");
        alertDialog.setMessage("Are you sure you want to delete all the workouts?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        workoutdatasource.clearAll();
                        workexedatasource.clearAll();
                        Intent addNewWorkout = new Intent("com.example.amandajonathan.workoutmanager.MainActivity");
                        startActivity(addNewWorkout);
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



} // closes ViewWorkoutList class
