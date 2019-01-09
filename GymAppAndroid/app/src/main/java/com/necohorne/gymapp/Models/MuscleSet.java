package com.necohorne.gymapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MuscleSet implements Parcelable {

    private String muscleName;
    private ArrayList<BlockExercise> exercises;

    public MuscleSet() {
    }

    //Constructor and methods needed for Parcelable
    protected MuscleSet(Parcel in) {
        muscleName = in.readString();
        exercises = in.createTypedArrayList(BlockExercise.CREATOR);
    }

    public static final Creator<MuscleSet> CREATOR = new Creator<MuscleSet>() {
        @Override
        public MuscleSet createFromParcel(Parcel in) {
            return new MuscleSet(in);
        }

        @Override
        public MuscleSet[] newArray(int size) {
            return new MuscleSet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(muscleName);
        dest.writeTypedList(exercises);
    }

    //Getters and Setters
    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public ArrayList<BlockExercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<BlockExercise> exercises) {
        this.exercises = exercises;
    }

    //Other Class Methods
}
