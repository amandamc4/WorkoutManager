// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;


/**
 * Created by Amanda on 10/25/2016.
 */

/*This activity allows the user to select one or more exercises to be included in their workout.
* The user may either click the checkbox to select an exercise, or click the exercise name to view
* a description of that exercise*/
public class AddExercises extends Activity  {

    public String[] exerciseName;
    public String[] exerciseDescription;
    public String[] exercisesSelected;
    private ExerciseListAddAdapter mAdapter;
    private boolean[] exerciseNameCheck;
    private ListView listView;
    private int request_Code = 1;
    private String weekDay;
    private String workoutDescription;
    private ExerciseDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercises);

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday");
        workoutDescription = intent.getStringExtra("description");
        startActivityForResult( new Intent( "com.example.amandajonathan.workoutmanager.WebApiConnect" ), request_Code );

    } // close onCreate

    public void onActivityResult( int requestCode, int resultCode, Intent data ){

        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {

                datasource = new ExerciseDataSource(this);
                datasource.open();

                String result = data.getStringExtra( "response" );

                try {
                    JSONObject jsonObject = new JSONObject( result );
                    JSONArray jsonArray = jsonObject.getJSONArray( "results" );

                    exerciseName =  new String[jsonArray.length()];
                    exerciseDescription =  new String[jsonArray.length()];

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                        exerciseName[i] = jsonObject2.getString("name");
                        exerciseDescription[i] = jsonObject2.getString("description");
                    } // end for

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                int count = datasource.isTableEmpty();

                if(count == 0){
                    for(int i =0; i<exerciseName.length; i++){
                        Exercise exercise = null;
                        exercise = datasource.createExercise(exerciseName[i], exerciseDescription[i]);
                    }
                }

                exerciseNameCheck = new boolean[exerciseName.length];
                mAdapter = new ExerciseListAddAdapter();

                listView = (ListView) findViewById(R.id.addExercises);
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                        Log.d("list clicked", exerciseName[position]);
                        Intent exerciseInfo = new Intent("com.example.amandajonathan.workoutmanager.ExerciseInfo");
                        exerciseInfo.putExtra("exerciseName", exerciseName[position]);
                        exerciseInfo.putExtra("exerciseDescription", exerciseDescription[position]);
                        startActivity(exerciseInfo);

                    }
                }); // closes OnItemClickListener
            }
        }
    } // Closes onActivityResult

    public void addSelected(View view) {
        int j=0;
        int count = 0;
        for(int i=0; i<exerciseNameCheck.length; i++){
            if(exerciseNameCheck[i] == true){
                count ++;
            }
        }
        if(count > 0){
            exercisesSelected =  new String[count];
            for(int i=0; i<exerciseNameCheck.length; i++){
                if(exerciseNameCheck[i] == true){
                    exercisesSelected[j] = exerciseName[i];
                    j++;
                }
            }

            Intent editWorkout = new Intent("com.example.amandajonathan.workoutmanager.EditWorkout");
            editWorkout.putExtra("weekday", weekDay );
            editWorkout.putExtra("workoutDescription", workoutDescription );
            editWorkout.putExtra("exercisesSelected", exercisesSelected);

            startActivity( editWorkout );
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(AddExercises.this).create();
            alertDialog.setTitle("No Exercise selected");
            alertDialog.setMessage("Please select at least one exercise");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    private static class ExercisesViewHolder {
        public CheckBox checks;
        public TextView name;
        public int position;
    }

    private class ExerciseListAddAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exerciseName.length;
        }

        @Override
        public String getItem(int position) {
            return exerciseName[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ExercisesViewHolder holder = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.add_exercises_listview, parent, false);
                holder = new ExercisesViewHolder();
                holder.checks = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.checks.setOnCheckedChangeListener(mStarCheckedChanceChangeListener);
                holder.name = (TextView) convertView.findViewById(R.id.exerciseNameLabel);
                holder.position = position;

                convertView.setTag(holder);
            } else {
                holder = (ExercisesViewHolder) convertView.getTag();
            }
            holder.checks.setOnCheckedChangeListener(null);
            holder.checks.setChecked(exerciseNameCheck[position]);
            holder.checks.setOnCheckedChangeListener(mStarCheckedChanceChangeListener);

            holder.name.setText(exerciseName[position]);


            return convertView;
        } // closes getView

    } // closes ExerciseListAdapter Class

        private OnCheckedChangeListener mStarCheckedChanceChangeListener = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final int position = listView.getPositionForView(buttonView);
                if (position != ListView.INVALID_POSITION) {
                    exerciseNameCheck[position] = isChecked;
                }
            }
        };

} // close AddExercises Class
