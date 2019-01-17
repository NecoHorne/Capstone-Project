package com.necohorne.gymapp.Utils.Data;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class MeasurementsConverter {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
