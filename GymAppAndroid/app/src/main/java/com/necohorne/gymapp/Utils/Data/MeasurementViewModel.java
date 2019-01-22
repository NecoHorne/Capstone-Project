package com.necohorne.gymapp.Utils.Data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.necohorne.gymapp.Models.Measurement;

import java.util.List;

public class MeasurementViewModel extends AndroidViewModel {

    private LiveData<List<Measurement>> measurements;

    public MeasurementViewModel(@NonNull Application application) {
        super(application);
        MeasurementsDatabase measurementsDatabase = MeasurementsDatabase.getInstance(this.getApplication());
        measurements = measurementsDatabase.MeasurementDao().loadAllMeasurements();
    }

    public LiveData<List<Measurement>> getMeasurements(){
        return measurements;
    }
}
