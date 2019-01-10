package com.necohorne.gymapp.Utils.Data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.necohorne.gymapp.Models.MuscleSet;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SetConverter {

    @TypeConverter
    public static ArrayList<MuscleSet> fromString(String value) {
        Type listType = new TypeToken<MuscleSet>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<MuscleSet> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
