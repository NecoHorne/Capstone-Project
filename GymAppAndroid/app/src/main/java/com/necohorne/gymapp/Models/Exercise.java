package com.necohorne.gymapp.Models;

import java.util.ArrayList;

public class Exercise {

    private String name;
    private String muscleTargeted;
    private String equipmentType;
    private String exerciseType;
    private ArrayList<String> instructions;

    public Exercise() {
    }

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
}
