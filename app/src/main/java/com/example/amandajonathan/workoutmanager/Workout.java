// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 11/9/2016.
 */

//Data model for Workouts

public class Workout {

    private long id;
    private String workoutDay;
    private String workoutDescription;
    private String createDate;

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkoutDay() {
        return workoutDay;
    }

    public void setWorkoutDay(String workoutDay) {
        this.workoutDay = workoutDay;
    }

    public String getWorkoutDescription(){
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription){this.workoutDescription = workoutDescription;}

    public String getCreateDate() { return createDate; }

    public void setCreateDate(String createDate){ this.createDate = createDate; }
}
