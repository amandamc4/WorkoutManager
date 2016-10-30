package com.example.amandajonathan.workoutmanager;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Amanda on 10/28/2016.
 */
public class EditListAdapter extends BaseAdapter {

    public int reps[];
    public Double weights[];
    private String[] exerciseNames;

    private Context context;
    CustomButtonListener customButtonListener;

    public class EditListViewHolder {

        TextView exerciseName ;
        Button addReps;
        EditText reps ;
        Button subtractReps;
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

    public void setCustomButtonListener(CustomButtonListener customButtonListner)
    {
        this.customButtonListener = customButtonListner;
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
            listViewHolder.addReps = (Button) row.findViewById(R.id.addReps);
            listViewHolder.reps = (EditText) row.findViewById(R.id.reps);
            listViewHolder.subtractReps = (Button) row.findViewById(R.id.subtractReps);
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

            listViewHolder.reps.setText(String.valueOf(reps[position]));
            listViewHolder.weight.setText(String.valueOf(weights[position]));


        } catch (Exception e) {
            e.printStackTrace();
        }
        listViewHolder.addReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, listViewHolder.reps, 1);
                    reps[position] = reps[position] + 1;
                }
            }
        });
        listViewHolder.subtractReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, listViewHolder.reps, -1);
                    if (reps[position] > 0)
                        reps[position] = reps[position] - 1;
                }
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

        for (int i=0; i<weights.length; i++){
            Log.d("weights", weights[i].toString());
        }


        return row;
    }
}
