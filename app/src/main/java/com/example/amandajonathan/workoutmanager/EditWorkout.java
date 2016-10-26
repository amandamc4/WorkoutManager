package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Amanda on 10/26/2016.
 */
public class EditWorkout extends Activity {

    private String weekDay;
    private String workoutDescription;
    private String[] exercises;

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

    } // close onCreate
}
