package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 11/11/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ViewExerciseListAdapter extends BaseAdapter {

    private Context context;
    public String[] exercises;
    public String[] reps;
    public Double weights[];
    public String dayWeek;
    public String description;

    public ViewExerciseListAdapter(Context context, List<WorkoutExercise> workouts) {
        this.context = context;

        exercises = new String[workouts.size()];
        reps = new String[workouts.size()];
        weights = new Double [workouts.size()];
        dayWeek = workouts.get(0).getWorkoutDay();
        description = workouts.get(0).getWorkoutDescription();

        for (int i=0; i<workouts.size(); i++){
            exercises[i] = workouts.get(i).getExerciseName();
            reps[i] = workouts.get(i).getReps();
            weights[i] = workouts.get(i).getWeight();
        }

    } // closes constructor

    @Override
    public int getCount() {
        return exercises.length;
    }

    @Override
    public String getItem(int position) {
        return exercises[position];
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        return null;
    }


} // closes ViewExerciseListAdapter class
