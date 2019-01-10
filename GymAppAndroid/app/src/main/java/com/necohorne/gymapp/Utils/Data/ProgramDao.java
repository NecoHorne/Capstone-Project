package com.necohorne.gymapp.Utils.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.necohorne.gymapp.Models.Program;

import java.util.List;

//data access objects
@Dao
public interface ProgramDao {

    @Query("SELECT * FROM program ORDER BY primaryKey")
    LiveData<List<Program>> loadAllPrograms();

//    @Query("SELECT * FROM program WHERE id = :''")
//    Program searchProgram(int searchProgramId);

    @Insert
    void insertProgram(Program program);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProgram(Program program);

    @Delete
    void deleteProgram(Program program);


}
