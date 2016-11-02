package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Amanda on 10/26/2016.
 */
public class EditWorkout extends Activity  {

    private String weekDay;
    private String workoutDescription;
    private String[] exercises;

    private ListView listView;
    private EditListAdapter listAdapter;

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
        listAdapter = new EditListAdapter(this,exercises);
        listView.setAdapter(listAdapter);


    } // close onCreate

    public void saveWorkout(View view) {

        for (int i = 0; i < listAdapter.reps.length; i++) {
            Log.d("Reps:", String.valueOf(listAdapter.reps[i]));
            Log.d("Weight:", String.valueOf(listAdapter.weights[i]));
        }
    }

}
