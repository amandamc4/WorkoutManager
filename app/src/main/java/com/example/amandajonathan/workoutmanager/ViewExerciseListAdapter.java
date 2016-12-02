// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 11/11/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

//Custom adapter for the exercise list
public class ViewExerciseListAdapter extends BaseAdapter {

    private Context context;
    public String[] exercises;
    public String[] reps;
    public Double weights[];
    public String dayWeek;
    public String description;

    public class WorkoutListViewHolder {
        TextView exerciseName ;
        TextView weight;
        TextView reps;
        int ref;
    }

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
        View row;
        final WorkoutListViewHolder listViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.view_workout_listview, parent, false);
            listViewHolder = new WorkoutListViewHolder();
            listViewHolder.exerciseName = (TextView) row.findViewById(R.id.exerciseName);
            listViewHolder.weight = (TextView) row.findViewById(R.id.weight);
            listViewHolder.reps = (TextView) row.findViewById(R.id.reps);
            row.setTag(listViewHolder);
        }else {
            row = convertView;
            listViewHolder = (WorkoutListViewHolder) row.getTag();
        }
        listViewHolder.ref = position;
        try {

            listViewHolder.exerciseName.setText(exercises[position]);
            listViewHolder.weight.setText(String.valueOf(weights[position]));
            listViewHolder.reps.setText(reps[position]);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    } // closes getView

} // closes ViewExerciseListAdapter class
