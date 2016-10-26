package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Amanda on 10/26/2016.
 */
public class ExerciseInfo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_info);

        Intent intent = getIntent();
        String name = intent.getStringExtra( "exerciseName" );
        String description = intent.getStringExtra( "exerciseDescription" );

        description = description.replaceAll("\\<p>", "");
        description = description.replaceAll("\\</p>", "");


        TextView textView = (TextView) findViewById(R.id.textViewName);
        textView.setText(name);

        TextView textView2 = (TextView) findViewById(R.id.textViewDescription);
        textView2.setText(description);


    } // close onCre
}
