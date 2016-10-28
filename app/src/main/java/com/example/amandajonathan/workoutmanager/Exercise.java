package com.example.amandajonathan.workoutmanager;

import java.util.Date;

/**
 * Created by Amanda on 10/28/2016.
 */
public class Exercise {
    private long id;
    private String exerciseName;
    private String exerciseReps;
    private double weight;
    private String dayOfWeek;
    private String createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseReps(){
        return exerciseReps;
    }

    public void setExerciseReps(String exerciseReps){
        this.exerciseReps = exerciseReps;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getDayOfWeek(){
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public String getCreateDate(){
        return createDate;
    }

    public void setCreateDate(String createDate){
        this.createDate = createDate;
    }
}
