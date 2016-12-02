// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Amanda on 10/28/2016.
 */

//Custom adapter class to hold the exercises selected by the user
public class EditListAdapter extends BaseAdapter {

    public int reps[];
    public Double weights[];
    private String[] exerciseNames;
    private Context context;

    public class EditListViewHolder {

        TextView exerciseName ;
        ImageButton addReps;
        EditText rep ;
        ImageButton subtractReps;
        EditText weight;
        int ref;
    }

    public EditListAdapter(Context context, String[] exerciseNames) {
        this.context = context;
        this.exerciseNames = exerciseNames;
        reps = new int[exerciseNames.length];
        weights = new Double[exerciseNames.length];
        for(int i=0; i<exerciseNames.length; i++){
            reps[i] = 0;
            weights[i] = 0.0;
        }
    }
    public EditListAdapter(Context context, List<WorkoutExercise> workout) {
        this.context = context;
        exerciseNames = new String[workout.size()];
        reps = new int[workout.size()];
        weights = new Double[workout.size()];

        for(int i=0; i<workout.size(); i++){
            exerciseNames[i] = workout.get(i).getExerciseName();
            reps[i] = Integer.parseInt(workout.get(i).getReps());
            weights[i] = workout.get(i).getWeight();
        }
    }


    @Override
    public int getCount() {
        return exerciseNames.length;
    }

    @Override
    public String getItem(int position) {
        return exerciseNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final EditListViewHolder listViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.edit_exercise_listview, parent, false);
            listViewHolder = new EditListViewHolder();
            listViewHolder.exerciseName = (TextView) row.findViewById(R.id.exerciseName);
            listViewHolder.addReps = (ImageButton) row.findViewById(R.id.addReps);
            listViewHolder.rep = (EditText) row.findViewById(R.id.reps);
            listViewHolder.subtractReps = (ImageButton) row.findViewById(R.id.subtractReps);
            listViewHolder.weight = (EditText) row.findViewById(R.id.weight);
            row.setTag(listViewHolder);
        } else {
            row = convertView;
            listViewHolder = (EditListViewHolder) row.getTag();
        }
        {
            listViewHolder.ref = position;
            listViewHolder.exerciseName.setText(exerciseNames[position]);
        }
        try {

            listViewHolder.rep.setText(String.valueOf(reps[position]));
            listViewHolder.weight.setText(String.valueOf(weights[position]));


        } catch (Exception e) {
            e.printStackTrace();
        }
        listViewHolder.addReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reps[position] = reps[position] + 1;
                listViewHolder.rep.setText(String.valueOf(reps[position]));
            }
        });
        listViewHolder.subtractReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reps[position] > 0){
                    reps[position] = reps[position] - 1;
                }
                listViewHolder.rep.setText(String.valueOf(reps[position]));
            }

        });
        listViewHolder.weight.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }
            @Override
            public void afterTextChanged(Editable arg0) {
               if(!arg0.toString().equals("")){
                    weights[listViewHolder.ref] = Double.parseDouble(arg0.toString());
               }
            }
        });

        listViewHolder.rep.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }
            @Override
            public void afterTextChanged(Editable arg0) {
                if(!arg0.toString().equals("")){
                    reps[listViewHolder.ref] = Integer.parseInt(arg0.toString());
                    if(reps[listViewHolder.ref] < 1){
                        listViewHolder.rep.setError("Reps should be greater than 1");
                    }
                }
            }
        });

        return row;
    }
}
