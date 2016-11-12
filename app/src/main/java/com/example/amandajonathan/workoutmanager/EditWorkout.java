package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Date;

/**
 * Created by Amanda on 10/26/2016.
 */
public class EditWorkout extends Activity {

    private ExerciseDataSource exedatasource;
    private WorkoutDataSource workdatasource;
    private WorkoutExerciseDataSource workexedatasource;
    private String weekDay;
    private String workoutDescription;
    private String[] exercises;
    private ListView listView;
    private EditListAdapter listAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise);

        workdatasource = new WorkoutDataSource(this);
        workdatasource.open();

        exedatasource = new ExerciseDataSource(this);
        exedatasource.open();

        workexedatasource = new WorkoutExerciseDataSource(this);
        workexedatasource.open();

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday");
        workoutDescription = intent.getStringExtra("workoutDescription");
        exercises = intent.getStringArrayExtra("exercisesSelected");

        TextView textView = (TextView) findViewById(R.id.textViewWeekDay);
        textView.setText(weekDay);

        listView = (ListView) findViewById(R.id.editExercises);
        listAdapter = new EditListAdapter(this, exercises);
        listView.setAdapter(listAdapter);

    } // close onCreate

    public void saveWorkout(View view) {

        boolean isZeroRep = false;
        String zeroReps = "";

        for (int i = 0; i < listAdapter.reps.length; i++) {
            Log.d("Reps:", String.valueOf(listAdapter.reps[i]));
            Log.d("Weight:", String.valueOf(listAdapter.weights[i]));
            if (listAdapter.reps[i] < 1) {
                isZeroRep = true;
                zeroReps += exercises[i] + ", ";
            }
        } // close For
        if (isZeroRep == true) {

            AlertDialog alertDialog = new AlertDialog.Builder(EditWorkout.this).create();
            alertDialog.setTitle("Alert!");
            alertDialog.setMessage("Values for reps cannot be 0!\nPlease fix values for:\n" +
                    "Reps: " + zeroReps + "\n");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{

            Date date = new Date();
            String dates = date.toString();
            Workout workout = null;
            workout = workdatasource.createWorkout(weekDay, workoutDescription,dates);

            for(int i=0; i<exercises.length; i++){
                long exeId = exedatasource.getExerciseByName(exercises[i]);
                workexedatasource.createWorkoutExercise(exeId, workout.getId(), String.valueOf(listAdapter.reps[i]), listAdapter.weights[i], exercises[i], workout.getWorkoutDay(), workout.getWorkoutDescription(), workout.getCreateDate());
            }
        }
        Intent viewWorkout = new Intent("com.example.amandajonathan.workoutmanager.ViewWorkoutList");
        viewWorkout.putExtra("weekday", weekDay );
        startActivity(viewWorkout);
    }//close saveWorkout

    public void addAnotherDay(View view) {
        boolean isZeroRep = false;
        String zeroReps = "";

        for (int i = 0; i < listAdapter.reps.length; i++) {
            Log.d("Reps:", String.valueOf(listAdapter.reps[i]));
            Log.d("Weight:", String.valueOf(listAdapter.weights[i]));
            if (listAdapter.reps[i] < 1) {
                isZeroRep = true;
                zeroReps += exercises[i] + ", ";
            }
        } // close For
        if (isZeroRep == true) {

            AlertDialog alertDialog = new AlertDialog.Builder(EditWorkout.this).create();
            alertDialog.setTitle("Alert!");
            alertDialog.setMessage("Values for reps cannot be 0!\nPlease fix values for:\n" +
                    "Reps: " + zeroReps + "\n");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{

            Date date = new Date();
            String dates = date.toString();
            Workout workout = null;
            workout = workdatasource.createWorkout(weekDay, workoutDescription,dates);

            for(int i=0; i<exercises.length; i++){
                long exeId = exedatasource.getExerciseByName(exercises[i]);
                workexedatasource.createWorkoutExercise(exeId, workout.getId(), String.valueOf(listAdapter.reps[i]), listAdapter.weights[i], exercises[i], workout.getWorkoutDay(), workout.getWorkoutDescription(), workout.getCreateDate());
            }
        }
        Intent addWeekDay = new Intent("com.example.amandajonathan.workoutmanager.AddDayWeek");
        startActivity(addWeekDay);
    }


} //close Class
