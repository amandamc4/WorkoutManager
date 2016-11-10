package com.example.amandajonathan.workoutmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String[] exerciseName;
    public String[] exerciseDescription;
    private ExerciseDataSource datasource;
    private int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivityForResult(new Intent("com.example.amandajonathan.workoutmanager.WebApiConnect"), request_Code);

//        List<Exercise> values = datasource.getAllExercises();
//        for(int i=0; i<values.size(); i++){String exename = values.get(i).getExerciseName();
//            Log.d("exercie", exename);
//        }
    }

    public void onClick(View view) {

        Intent addDayWeek = new Intent("com.example.amandajonathan.workoutmanager.AddDayWeek");

        startActivity( addDayWeek );

    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        datasource = new ExerciseDataSource(this);
//        datasource.open();
//
//        if (requestCode == request_Code) {
//            if (resultCode == RESULT_OK) {
//
//                String result = data.getStringExtra("response");
//
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    JSONArray jsonArray = jsonObject.getJSONArray("results");
//
//                    exerciseName = new String[jsonArray.length()];
//                    exerciseDescription = new String[jsonArray.length()];
//                    //Log.i("INFO", jsonArray.toString());
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//
//                        exerciseName[i] = jsonObject2.getString("name");
//                        exerciseDescription[i] = jsonObject2.getString("description");
//
//                        Exercise exercise = null;
//
//                        exercise = datasource.createExercise(exerciseName[i], exerciseDescription[i]);
//
//                    } // end for
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                List<Exercise> values = datasource.getAllExercises();
//                for(int i=0; i<values.size(); i++){
//                    String exename = values.get(i).getExerciseName();
//                    Log.d("exercie", exename);
//                }
//            }
//        }
    //}
}
