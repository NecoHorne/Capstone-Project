package com.necohorne.gymapp.Utils.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.necohorne.gymapp.Models.Measurement;

@Database(entities = {Measurement.class}, version = 1, exportSchema = false)
@TypeConverters(MeasurementsConverter.class)
public abstract class MeasurementsDatabase extends RoomDatabase {

    private static final String TAG = MeasurementsDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "MeasurementsDatabase";
    private static MeasurementsDatabase sInstance;

    public static MeasurementsDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized(LOCK) {
//                Log.d(TAG, "Creating new Database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MeasurementsDatabase.class, MeasurementsDatabase.DATABASE_NAME)
                        .build();
            }
        }
//        Log.d(TAG, "Getting database instance");
        return sInstance;
    }

    public abstract MeasurementDao MeasurementDao();
}
