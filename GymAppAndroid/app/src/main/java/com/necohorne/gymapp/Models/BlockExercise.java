package com.necohorne.gymapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class BlockExercise implements Parcelable {

    private Exercise mExercise;
    private int numberSets;
    private String[] repsPerSet;

    public BlockExercise() {
    }

    //Constructor and methods needed for Parcelable
    protected BlockExercise(Parcel in) {
        //Custom object exercises
        mExercise = in.readParcelable(mExercise.getClass().getClassLoader());
        numberSets = in.readInt();
        repsPerSet = in.createStringArray();
    }

    public static final Creator<BlockExercise> CREATOR = new Creator<BlockExercise>() {
        @Override
        public BlockExercise createFromParcel(Parcel in) {
            return new BlockExercise(in);
        }

        @Override
        public BlockExercise[] newArray(int size) {
            return new BlockExercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mExercise, flags);
        dest.writeInt(numberSets);
        dest.writeStringArray(repsPerSet);
    }

    //Getters and Setters
    public Exercise getExercise() {
        return mExercise;
    }

    public void setExercise(Exercise exercise) {
        mExercise = exercise;
    }

    public int getNumberSets() {
        return numberSets;
    }

    public void setNumberSets(int numberSets) {
        this.numberSets = numberSets;
    }

    public String[] getRepsPerSet() {
        return repsPerSet;
    }

    public void setRepsPerSet(String[] repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    @Override
    public String toString() {
        return "BlockExercise{" +
                "mExercise=" + mExercise +
                ", numberSets=" + numberSets +
                ", repsPerSet=" + Arrays.toString(repsPerSet) +
                '}';
    }

    //Other Class Methods
}
