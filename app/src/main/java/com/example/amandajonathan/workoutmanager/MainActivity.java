package com.example.amandajonathan.workoutmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public String[] exerciseName;
    private WorkoutDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ds = new WorkoutDataSource(this);
        ds.open();

        int count = ds.isTableEmpty();

        if(count > 0){
            List<String> daysAvailable = ds.getAllWorkoutDays();
            Intent viewWorkout = new Intent("com.example.amandajonathan.workoutmanager.ViewWorkoutList");
            viewWorkout.putExtra("weekday", daysAvailable.get(0));
            startActivity( viewWorkout );
        }
    }

    public void onClick(View view) {

        Intent addDayWeek = new Intent("com.example.amandajonathan.workoutmanager.AddDayWeek");
        startActivity( addDayWeek );

    }

}
