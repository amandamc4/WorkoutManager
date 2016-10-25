package com.example.amandajonathan.workoutmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
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
public class AddExercises extends Activity implements AdapterView.OnItemClickListener {
    private TextView textView;
    static final String API_URL = "https://wger.de/api/v2/exercise/?format=json&language=2";
    public String[] exerciseName;
    public String exerciseDescription;
    private ExerciseListAddAdapter mAdapter;
    private boolean[] exerciseNameCheck;
    private ListView listView;

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
            //textView = (TextView) findViewById(R.id.responseView);
            //textView.setText(response);

            try {
                JSONObject jsonObject = new JSONObject( response );
                JSONArray jsonArray = jsonObject.getJSONArray( "results" );

                exerciseName =  new String[jsonArray.length()];

                Log.i("INFO", jsonArray.toString());

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                    exerciseName[i] = jsonObject2.getString("name");
                    exerciseDescription = jsonObject2.getString("description");

                    Log.d( "JSON", exerciseName[i] );
                    Log.d( "JSON", exerciseDescription);

                } // end for

            } catch (JSONException e) {
                e.printStackTrace();
            }
            exerciseNameCheck = new boolean[exerciseName.length];
            mAdapter = new ExerciseListAddAdapter();

            listView = (ListView) findViewById(R.id.addExercises);
            listView.setAdapter(mAdapter);
            //listView.setOnItemClickListener(this);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                    Log.d("list clicked", exerciseName[position]);

                }
            });

        } //closes onPostExecute Function
    } // closes RetrieveFeedTask class



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercises);

        new RetrieveFeedTask().execute();

    } // close onCreate

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("List clicked", exerciseName[position]);
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
                holder.name = (TextView) convertView.findViewById(R.id.exerciseName);
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
                Log.d("position checked", exerciseName[position]);
            }
        }
    };

} // close AddExercises Class
