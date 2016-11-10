package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;
import java.util.List;

/**
 * Created by Amanda on 10/26/2016.
 */
public class EditWorkout extends Activity {

    private ExerciseDataSource exedatasource;
    private WorkoutDataSource workdatasource;
    private String weekDay;
    private String workoutDescription;
    private String[] exercises;

    private ListView listView;
    private EditListAdapter listAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise);

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday");
        workoutDescription = intent.getStringExtra("description");
        exercises = intent.getStringArrayExtra("exercisesSelected");

        TextView textView = (TextView) findViewById(R.id.textViewWeekDay);
        textView.setText(weekDay);

        listView = (ListView) findViewById(R.id.editExercises);
        listAdapter = new EditListAdapter(this, exercises);
        listView.setAdapter(listAdapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } // close onCreate

    public void saveWorkout(View view) {
        //List<Exercise> values = datasource.getAllExercises();


//        for(int i=0; i<exercises.length; i++){
//            long exeId = exedatasource.getExerciseByName(exercises[i]);
//            Log.d("exeid", String.valueOf(exeId));
//        }

        Date date = new Date();
        String dates = date.toString();
        Workout workout = null;
        workout = workdatasource.createWorkout(weekDay, workoutDescription,dates);


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
    }//close saveWorkout

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditWorkout Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.amandajonathan.workoutmanager/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditWorkout Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.amandajonathan.workoutmanager/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
