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
    public String weekDay;
    public String description;
    private int request_Code = 1;


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
                //Log.v("item", (String) parent.getItemAtPosition(position));
                int index = parent.getSelectedItemPosition();
                weekDay = daysOfWeek[index];
                //Log.v("item", daysOfWeek[index]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        }); // Closes onItemSelected


    } // closes onCreate

    public void addExercises(View view) {

        EditText descriptionEdit = (EditText) findViewById(R.id.descriptionText);
        description = descriptionEdit.getText().toString();
        Log.v("description", description);
        Log.v("item", weekDay);

        Intent addExercises = new Intent("com.example.amandajonathan.workoutmanager.AddExercises");

        addExercises.putExtra("weekday", weekDay);
        addExercises.putExtra("description", description);


        startActivity(addExercises);

    }



}


