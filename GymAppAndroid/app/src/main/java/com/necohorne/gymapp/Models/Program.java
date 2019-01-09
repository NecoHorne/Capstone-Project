package com.necohorne.gymapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Program implements Parcelable {

    private Date date;
    private String day;
    //todo add arraylist to Parcelable object
    private ArrayList<MuscleSet> sets;

    public Program() {
    }

    protected Program(Parcel in) {
        day = in.readString();
    }

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<MuscleSet> getSets() {
        return sets;
    }

    public void setSets(ArrayList<MuscleSet> sets) {
        this.sets = sets;
    }
}
