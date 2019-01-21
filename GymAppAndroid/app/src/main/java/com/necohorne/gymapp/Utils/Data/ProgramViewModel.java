package com.necohorne.gymapp.Utils.Data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.necohorne.gymapp.Models.Program;

import java.util.List;

public class ProgramViewModel extends AndroidViewModel {

    private LiveData<List<Program>> programs;

    public ProgramViewModel(@NonNull Application application) {
        super(application);
        ProgramDatabase programDatabase = ProgramDatabase.getInstance(this.getApplication());
        programs = programDatabase.ProgramDao().loadAllPrograms();
    }

    public LiveData<List<Program>> getPrograms(){
        return programs;
    }
}
