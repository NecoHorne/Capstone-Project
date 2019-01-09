package com.necohorne.gymapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BlockExercise implements Parcelable {

    //todo Add custom object to parcelable method
    private Exercise mExercise;
    private int numberSets;
    private String[] repsPerSet;

    public BlockExercise() {
    }

    protected BlockExercise(Parcel in) {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberSets);
        dest.writeStringArray(repsPerSet);
    }
}
