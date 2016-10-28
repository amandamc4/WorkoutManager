package com.example.amandajonathan.workoutmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Amanda on 10/26/2016.
 */
public class EditWorkout extends Activity implements CustomButtonListener {

    private String weekDay;
    private String workoutDescription;
    private String[] exercises;

    private ListView listView;
    private EditListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise);

        Intent intent = getIntent();
        weekDay = intent.getStringExtra("weekday");
        workoutDescription = intent.getStringExtra("description");
        exercises = intent.getStringArrayExtra("exercisesSelected");

        TextView textView = (TextView) findViewById(R.id.textViewWeekDay);
        textView.setText(weekDay);

        listView = (ListView) findViewById(R.id.editExercises);
        listAdapter = new EditListAdapter(this,exercises);
        listView.setAdapter(listAdapter);
        listAdapter.setCustomButtonListener(this);


    } // close onCreate

    @Override
    public void onButtonClickListener(int position, EditText editText, int value) {
        /*
        View view = listView.getChildAt(position);*/
        int rep = Integer.parseInt(editText.getText().toString());
        rep = rep + value;
        editText.setText(rep);
    }
}
