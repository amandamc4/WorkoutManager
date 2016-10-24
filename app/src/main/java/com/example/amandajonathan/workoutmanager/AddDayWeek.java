package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import static com.example.amandajonathan.workoutmanager.R.array.dayOfWeek;

/**
 * Created by Amanda on 10/24/2016.
 */
public class AddDayWeek extends Activity {

    private String[] daysOfWeek;
    private Spinner dropdown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dayweek);

        daysOfWeek = getResources().getStringArray(dayOfWeek);

        dropdown = (Spinner) findViewById(R.id.daysWeek_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysOfWeek);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        EditText description = (EditText) findViewById(R.id.description);

        Intent addExercises = new Intent();

        addExercises.putExtra( "description", description.getText().toString() );


    } // closes onCreate

}
