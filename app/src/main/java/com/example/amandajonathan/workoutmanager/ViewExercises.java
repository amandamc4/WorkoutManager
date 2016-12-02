// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Amanda on 11/16/2016.
 */

/*This activity is called when the user wishes to see the list of available exercises by clicking on
the menu. When a user selects an exercise, they will be shown a new screen with more information
about the selected exercise*/

public class ViewExercises extends AppCompatActivity {

    private ExerciseDataSource datasource;
    String[] exercises;
    String[] descriptions;
    private ListView listView;
    EditText editSearch;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exercise);

        listView = (ListView) findViewById(R.id.exercises);
        editSearch = (EditText) findViewById(R.id.editSearch);

        datasource = new ExerciseDataSource(this);
        datasource.open();

        List<Exercise> values = datasource.getAllExercises();

        exercises = new String[values.size()];
        descriptions = new String[values.size()];

        for(int i=0; i<values.size(); i++){
            exercises[i] = values.get(i).getExerciseName();
            descriptions[i] = values.get(i).getExerciseDescription();
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, exercises);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent exerciseInfo = new Intent("com.example.amandajonathan.workoutmanager.ExerciseInfo");
                exerciseInfo.putExtra("exerciseName", exercises[position]);
                exerciseInfo.putExtra("exerciseDescription", descriptions[position]);
                startActivity(exerciseInfo);

            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ViewExercises.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
