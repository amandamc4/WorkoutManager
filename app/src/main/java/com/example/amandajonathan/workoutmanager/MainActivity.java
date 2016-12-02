// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.List;

/*Starting activity for the app. If there are no workouts currently saved (e.g. first run), then the
        * screen will only display a button to add a new workout. If there are workouts in the database,
        * then the ViewWorkoutList activity will be invoked and the user will be able to view all of their
        * stored workouts for each day.*/

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
