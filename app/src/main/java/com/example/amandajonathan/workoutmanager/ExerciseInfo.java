package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Amanda on 10/26/2016.
 */
public class ExerciseInfo extends Activity {

    ImageView myImage;

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

        myImage = (ImageView) findViewById(R.id.imageView);

        switch (name) {
            case "2 Handed Kettlebell Swing" :
                myImage.setImageResource(R.drawable.twohanded);
                return;
            case "Arnold Shoulder Press" :
                myImage.setImageResource(R.drawable.arnoldpress);
                return;
            case "Axe Hold" :
                myImage.setImageResource(R.drawable.axehold);
                return;
            case "Barbell Ab Rollout" :
                myImage.setImageResource(R.drawable.barbelabbs);
                return;
            case "Barbell Hack Squats" :
                myImage.setImageResource(R.drawable.barbellhack);
                return;
            case "Barbell Lunges" :
                myImage.setImageResource(R.drawable.barbellunges);
                return;
            case "Barbell Triceps Extension" :
                myImage.setImageResource(R.drawable.barbeltriceps);
                return;
            case "Bear Walk" :
                myImage.setImageResource(R.drawable.bearwalk);
                return;
            case "Bench Press" :
                myImage.setImageResource(R.drawable.benchpress);
                return;
            case "Benchpress Dumbbells" :
                myImage.setImageResource(R.drawable.benchdumbels);
                return;
            case "Bench Press Narrow Grip" :
                myImage.setImageResource(R.drawable.benchnarrow);
                return;
            case "Bent High Pulls" :
                myImage.setImageResource(R.drawable.benchhigh);
                return;
            case "Bent Over Barbell Row" :
                myImage.setImageResource(R.drawable.bentover);
                return;
            case "Bentover Dumbbell Rows" :
                myImage.setImageResource(R.drawable.bentoverdumb);
                return;
            case "Bent-over Lateral Raises" :
                myImage.setImageResource(R.drawable.bentlateral);
                return;
            default:
                myImage.setImageResource(R.drawable.workoutmanager);
                return;
        }

    } // close onCre
}
