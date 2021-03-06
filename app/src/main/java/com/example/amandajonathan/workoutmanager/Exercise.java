// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;


/**
 * Created by Amanda on 10/28/2016.
 */

//Data model for exercise
public class Exercise {
    private long id;
    private String exerciseName;
    private String exerciseDescription;


    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription(){
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription){this.exerciseDescription = exerciseDescription;}

}
