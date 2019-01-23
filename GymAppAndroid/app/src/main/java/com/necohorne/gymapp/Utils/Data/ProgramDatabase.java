package com.necohorne.gymapp.Utils.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.necohorne.gymapp.Models.Program;

@Database(entities = {Program.class}, version = 1, exportSchema = false)
@TypeConverters(ProgramConverter.class)
public abstract class ProgramDatabase extends RoomDatabase {

    private static final String TAG = ProgramDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "programDatabase";
    private static ProgramDatabase sInstance;

    public static ProgramDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized(LOCK) {
                Log.d(TAG, "Creating new Database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        ProgramDatabase.class, ProgramDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(TAG, "Getting database instance");
        return sInstance;
    }

    public abstract ProgramDao ProgramDao();

}
