package com.necohorne.gymapp.Utils.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.necohorne.gymapp.Models.Measurement;

import java.util.List;

//data access objects
@Dao
public interface MeasurementDao {

    @Query("SELECT * FROM measurement ORDER BY primaryKey")
    LiveData<List<Measurement>> loadAllMeasurements();

//    @Query("SELECT * FROM measurement WHERE day = :searchDay")
//    Measurement searchMeasurement(String searchDay);

    @Query("SELECT count(*) FROM measurement")
    int dbCount();

    //Return the last item added to the DB.
    @Query("SELECT * FROM measurement ORDER BY primaryKey DESC limit 1" )
    Measurement lastMeasurement();

    @Insert
    void insertMeasurement(Measurement measurement);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMeasurement(Measurement measurement);

    @Delete
    void deleteMeasurement(Measurement measurement);

}
