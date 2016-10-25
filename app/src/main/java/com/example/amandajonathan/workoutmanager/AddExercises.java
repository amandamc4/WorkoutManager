package com.example.amandajonathan.workoutmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Amanda on 10/25/2016.
 */
public class AddExercises extends Activity {
    private TextView textView;
    static final String API_URL = "https://wger.de/api/v2/exercise/?format=json&language=2";
    public String exerciseName;
    public String exerciseDescription;

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;
        protected String doInBackground(Void... urls) {

            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }

            //Log.i("INFO", response);
            textView = (TextView) findViewById(R.id.responseView);
            textView.setText(response);

            try {
                JSONObject jsonObject = new JSONObject( response );
                JSONArray jsonArray = jsonObject.getJSONArray( "results" );

                Log.i("INFO", jsonArray.toString());

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                    exerciseName = jsonObject2.getString("name");
                    exerciseDescription = jsonObject2.getString("description");

                    Log.d( "JSON", exerciseName );
                    Log.d( "JSON", exerciseDescription);

                } // end for



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercises);

        new RetrieveFeedTask().execute();
    }
}
