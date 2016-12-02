// Amanda Marques and Jonathan Desmond
// MAP524 Project - Workout Manager
// 02/12/2016

package com.example.amandajonathan.workoutmanager;

/**
 * Created by Amanda on 11/9/2016.
 */

//Data model for WorkoutExercise
//this is a bridge object between a Workout and an Exercise

public class WorkoutExercise {
    private long id;
    private long exerciseId;
    private long workoutId;
    private String reps;
    private double weight;
    private String exerciseName;
    private String workoutDay;
    private String workoutDescription;
    private String createDate;

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public long getExerciseId() {return exerciseId;}

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public long getWorkoutId() {return workoutId;}

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public double getWeight() { return weight; }

    public void setWeight (double weight) { this.weight = weight; }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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

    public String getCreateDate() {return createDate; }

    public void setCreateDate(String createDate) { this.createDate = createDate; }

}
