package com.necohorne.gymapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Exercise implements Parcelable {

    private String name;
    private String muscleTargeted;
    private String equipmentType;
    private String exerciseType;
    private ArrayList<String> instructions;

    public Exercise() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(muscleTargeted);
        dest.writeString(equipmentType);
        dest.writeString(exerciseType);
        dest.writeStringList(instructions);
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        muscleTargeted = in.readString();
        equipmentType = in.readString();
        exerciseType = in.readString();
        instructions = in.createStringArrayList();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleTargeted() {
        return muscleTargeted;
    }

    public void setMuscleTargeted(String muscleTargeted) {
        this.muscleTargeted = muscleTargeted;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    //the to string method will only return the exercise name in order to use it in the spinner.
    public String toString() {
        return name ;
    }
}
