package com.necohorne.gymapp.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.necohorne.gymapp.Models.Measurement;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.MeasurementViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProgressActivity extends AppCompatActivity {

    public static final String TAG = ProgressActivity.class.getSimpleName();
    private AnyChartView mChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mChartView = findViewById(R.id.chart_view);
        engageMeasureViewModel();
    }

    private void engageMeasureViewModel(){
        MeasurementViewModel viewModel = ViewModelProviders.of(ProgressActivity.this).get(MeasurementViewModel.class);
        viewModel.getMeasurements().observe(ProgressActivity.this, new Observer<List<Measurement>>(){

            @Override
            public void onChanged(@Nullable List<Measurement> measurements) {
                ArrayList<Measurement> measurementArrayList = (ArrayList<Measurement>) measurements;
                Intent intent = getIntent();
                String bodyPart = intent.getStringExtra(Constants.INTENT_PROGRESS);
                initChart(bodyPart, measurementArrayList);
            }
        });
    }

    private void initChart(String bodyPart, ArrayList<Measurement> measurementArrayList) {
        setTitle(bodyPart + getString(R.string.title_progress));
        switch(bodyPart){
            case "Neck":
                populateSingle(bodyPart, getNeckData(measurementArrayList));
                break;
            case "Chest":
                populateSingle(bodyPart, getChestData(measurementArrayList));
                break;
            case "Shoulder":
                populateSingle(bodyPart, getShoulderData(measurementArrayList));
                break;
            case "Left Arm":
                populateSingle(bodyPart, getLeftArmData(measurementArrayList));
                break;
            case "Right Arm":
                populateSingle(bodyPart, getRightArmData(measurementArrayList));
                break;
            case "Left Forearm":
                populateSingle(bodyPart, getLeftForearmData(measurementArrayList));
                break;
            case "Right Forearm":
                populateSingle(bodyPart, getRightForearmData(measurementArrayList));
                break;
            case "Waist":
                populateSingle(bodyPart, getWaistData(measurementArrayList));
                break;
            case "Hips":
                populateSingle(bodyPart, getHipsData(measurementArrayList));
                break;
            case "Left Thigh":
                populateSingle(bodyPart, getLeftThighData(measurementArrayList));
                break;
            case "Right Thigh":
                populateSingle(bodyPart, getRightThighData(measurementArrayList));
                break;
            case "Left Calf":
                populateSingle(bodyPart, getLeftCalfData(measurementArrayList));
                break;
            case "Right Calf":
                populateSingle(bodyPart, getRightCalfData(measurementArrayList));
                break;
            case "Weight":
                populateSingle(bodyPart, getWeightData(measurementArrayList));
                break;
            case "Body Fat":
                populateSingle(bodyPart, getBodyFatData(measurementArrayList));
                break;
            case "All":
                populateChart(measurementArrayList);
                break;
        }
    }

    //------------Single Data------------//

    public List<DataEntry> getNeckData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number neck = (measurement.getNeck() == 0) ? null : measurement.getNeck();
                seriesData.add(new CustomSingleDataEntry(week, neck));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number neck = (measurement.getNeck() == 0) ? null : measurement.getNeck();
                seriesData.add(new CustomSingleDataEntry(week, neck));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getChestData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number chest = (measurement.getChest() == 0) ? null : measurement.getChest();
                seriesData.add(new CustomSingleDataEntry(week, chest));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number chest = (measurement.getChest() == 0) ? null : measurement.getChest();
                seriesData.add(new CustomSingleDataEntry(week, chest));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getShoulderData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number shoulder = (measurement.getShoulders() == 0) ? null : measurement.getShoulders();
                seriesData.add(new CustomSingleDataEntry(week, shoulder));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number shoulder = (measurement.getShoulders() == 0) ? null : measurement.getShoulders();
                seriesData.add(new CustomSingleDataEntry(week, shoulder));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getLeftArmData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftArm = (measurement.getLeftArm() == 0) ? null : measurement.getLeftArm();
                seriesData.add(new CustomSingleDataEntry(week, leftArm));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftArm = (measurement.getLeftArm() == 0) ? null : measurement.getLeftArm();
                seriesData.add(new CustomSingleDataEntry(week, leftArm));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getRightArmData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightArm = (measurement.getRightArm() == 0) ? null : measurement.getRightArm();
                seriesData.add(new CustomSingleDataEntry(week, rightArm));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightArm = (measurement.getRightArm() == 0) ? null : measurement.getRightArm();
                seriesData.add(new CustomSingleDataEntry(week, rightArm));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getLeftForearmData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftForearm = (measurement.getLeftForearm() == 0) ? null : measurement.getLeftForearm();
                seriesData.add(new CustomSingleDataEntry(week, leftForearm));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftForearm = (measurement.getLeftForearm() == 0) ? null : measurement.getLeftForearm();
                seriesData.add(new CustomSingleDataEntry(week, leftForearm));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getRightForearmData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightForearm = (measurement.getRightForearm() == 0) ? null : measurement.getRightForearm();
                seriesData.add(new CustomSingleDataEntry(week, rightForearm));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightForearm = (measurement.getRightForearm() == 0) ? null : measurement.getRightForearm();
                seriesData.add(new CustomSingleDataEntry(week, rightForearm));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getWaistData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number waist = (measurement.getWaist() == 0) ? null : measurement.getWaist();
                seriesData.add(new CustomSingleDataEntry(week, waist));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number waist = (measurement.getWaist() == 0) ? null : measurement.getWaist();
                seriesData.add(new CustomSingleDataEntry(week, waist));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getHipsData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number hips = (measurement.getHips() == 0) ? null : measurement.getHips();
                seriesData.add(new CustomSingleDataEntry(week, hips));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number hips = (measurement.getHips() == 0) ? null : measurement.getHips();
                seriesData.add(new CustomSingleDataEntry(week, hips));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getLeftThighData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftThigh = (measurement.getLeftLeg() == 0) ? null : measurement.getLeftLeg();
                seriesData.add(new CustomSingleDataEntry(week, leftThigh));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftThigh = (measurement.getLeftLeg() == 0) ? null : measurement.getLeftLeg();
                seriesData.add(new CustomSingleDataEntry(week, leftThigh));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getRightThighData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightThigh = (measurement.getRightLeg() == 0) ? null : measurement.getRightLeg();
                seriesData.add(new CustomSingleDataEntry(week, rightThigh));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightThigh = (measurement.getRightLeg() == 0) ? null : measurement.getRightLeg();
                seriesData.add(new CustomSingleDataEntry(week, rightThigh));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getLeftCalfData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftCalf = (measurement.getLeftCalf() == 0) ? null : measurement.getLeftCalf();
                seriesData.add(new CustomSingleDataEntry(week, leftCalf));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number leftCalf = (measurement.getLeftCalf() == 0) ? null : measurement.getLeftCalf();
                seriesData.add(new CustomSingleDataEntry(week, leftCalf));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getRightCalfData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightCalf = (measurement.getRightCalf() == 0) ? null : measurement.getRightCalf();
                seriesData.add(new CustomSingleDataEntry(week, rightCalf));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number rightCalf = (measurement.getRightCalf() == 0) ? null : measurement.getRightCalf();
                seriesData.add(new CustomSingleDataEntry(week, rightCalf));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getWeightData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number weight = (measurement.getWeight() == 0) ? null : measurement.getWeight();
                seriesData.add(new CustomSingleDataEntry(week, weight));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number weight = (measurement.getWeight() == 0) ? null : measurement.getWeight();
                seriesData.add(new CustomSingleDataEntry(week, weight));
            }
        }

        return seriesData;
    }

    public List<DataEntry> getBodyFatData(ArrayList<Measurement> measurementArrayList){
        List<DataEntry> seriesData = new ArrayList<>();
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number bodyFat = (measurement.getBodyFat() == 0) ? null : measurement.getBodyFat();
                seriesData.add(new CustomSingleDataEntry(week, bodyFat));
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());
                Number bodyFat = (measurement.getBodyFat() == 0) ? null : measurement.getBodyFat();
                seriesData.add(new CustomSingleDataEntry(week, bodyFat));
            }
        }

        return seriesData;
    }

    private void populateSingle(String bodyPart, List<DataEntry> seriesData){

        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(getString(R.string.chart_title));

        cartesian.yAxis(0).title(getString(R.string.change));
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(bodyPart);
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series1.labels(true);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        mChartView.setChart(cartesian);

    }

    //------------All Data------------//

    private List<DataEntry> analyseData(ArrayList<Measurement> measurementArrayList){

        List<DataEntry> seriesData = new ArrayList<>();
        //check all the measurements saved in the database.
        //chart to only show last 10 measurements or if there are less than ten, show all.
        if(measurementArrayList.size() >= 10){
            int count = 10;
            while(count > 0){
                Measurement measurement = measurementArrayList.get(measurementArrayList.size() - count);
                CustomDataEntry dataEntry = getDataEntry(measurement);
                seriesData.add(dataEntry);
                count--;
            }
        } else {
            for(int i = 0; i < measurementArrayList.size(); i++) {
                Measurement measurement = measurementArrayList.get(i);
                CustomDataEntry dataEntry = getDataEntry(measurement);
                seriesData.add(dataEntry);
            }
        }
        return  seriesData;
    }

    private CustomDataEntry getDataEntry(Measurement measurement){

        //get the calendar week number
        String week = getString(R.string.calander_week) + getCalendarWeek(measurement.getDate());

        //for each data object in the measurement if its added as 0 mark as null for purposes of the chart
        Number neck = (measurement.getNeck() == 0) ? null : measurement.getNeck();
        Number chest = (measurement.getChest() == 0) ? null : measurement.getChest();
        Number shoulder = (measurement.getShoulders() == 0) ? null : measurement.getShoulders();
        Number leftArm = (measurement.getLeftArm() == 0) ? null : measurement.getLeftArm();
        Number rightArm = (measurement.getRightArm() == 0) ? null : measurement.getRightArm();
        Number leftForearm = (measurement.getLeftForearm() == 0) ? null : measurement.getLeftForearm();
        Number rightForearm = (measurement.getRightForearm() == 0) ? null : measurement.getRightForearm();
        Number waist = (measurement.getWaist() == 0) ? null : measurement.getWaist();
        Number hips = (measurement.getHips() == 0) ? null : measurement.getHips();
        Number leftThigh = (measurement.getLeftLeg() == 0) ? null : measurement.getLeftLeg();
        Number rightThigh = (measurement.getRightLeg() == 0) ? null : measurement.getRightLeg();
        Number leftCalf = (measurement.getLeftCalf() == 0) ? null : measurement.getLeftCalf();
        Number rightCalf = (measurement.getRightCalf() == 0) ? null : measurement.getRightCalf();
        Number weight = (measurement.getWeight() == 0) ? null : measurement.getWeight();
        Number bodyFat = (measurement.getBodyFat() == 0) ? null : measurement.getBodyFat();

        //finally return the data entry to insert in the series data.
        return new CustomDataEntry(week, neck, chest, shoulder, leftArm, rightArm, leftForearm, rightForearm, waist, hips, leftThigh, rightThigh, leftCalf, rightCalf, weight, bodyFat);
    }

    private void populateChart(ArrayList<Measurement> measurementArrayList) {
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(getString(R.string.chart_title));

        cartesian.yAxis(0).title(getString(R.string.change));
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);


        List<DataEntry> seriesData = analyseData(measurementArrayList);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'neck' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'chest' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'shoulder' }");
        Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'leftArm' }");
        Mapping series5Mapping = set.mapAs("{ x: 'x', value: 'rightArm' }");
        Mapping series6Mapping = set.mapAs("{ x: 'x', value: 'leftForearm' }");
        Mapping series7Mapping = set.mapAs("{ x: 'x', value: 'rightForearm' }");
        Mapping series8Mapping = set.mapAs("{ x: 'x', value: 'waist' }");
        Mapping series9Mapping = set.mapAs("{ x: 'x', value: 'hips' }");
        Mapping series10Mapping = set.mapAs("{ x: 'x', value: 'leftLeg' }");
        Mapping series11Mapping = set.mapAs("{ x: 'x', value: 'rightLeg' }");
        Mapping series12Mapping = set.mapAs("{ x: 'x', value: 'leftCalf' }");
        Mapping series13Mapping = set.mapAs("{ x: 'x', value: 'rightCalf' }");
        Mapping series14Mapping = set.mapAs("{ x: 'x', value: 'weight' }");
        Mapping series15Mapping = set.mapAs("{ x: 'x', value: 'bodyFat' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(getString(R.string.neck));
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series1.labels(true);

        Line series2 = cartesian.line(series2Mapping);
        series2.name(getString(R.string.chest));
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series2.labels(true);

        Line series3 = cartesian.line(series3Mapping);
        series3.name(getString(R.string.shoulder));
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series3.labels(true);

        Line series4 = cartesian.line(series4Mapping);
        series4.name(getString(R.string.left_arm));
        series4.hovered().markers().enabled(true);
        series4.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series4.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series4.labels(true);

        Line series5 = cartesian.line(series5Mapping);
        series5.name(getString(R.string.right_arm));
        series5.hovered().markers().enabled(true);
        series5.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series5.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series5.labels(true);

        Line series6 = cartesian.line(series6Mapping);
        series6.name(getString(R.string.left_forearm));
        series6.hovered().markers().enabled(true);
        series6.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series6.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series6.labels(true);

        Line series7 = cartesian.line(series7Mapping);
        series7.name(getString(R.string.right_forearm));
        series7.hovered().markers().enabled(true);
        series7.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series7.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series7.labels(true);

        Line series8 = cartesian.line(series8Mapping);
        series8.name(getString(R.string.waist));
        series8.hovered().markers().enabled(true);
        series8.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series8.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series8.labels(true);

        Line series9 = cartesian.line(series9Mapping);
        series9.name(getString(R.string.hips));
        series9.hovered().markers().enabled(true);
        series9.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series9.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series9.labels(true);

        Line series10 = cartesian.line(series10Mapping);
        series10.name(getString(R.string.left_thigh));
        series10.hovered().markers().enabled(true);
        series10.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series10.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series10.labels(true);

        Line series11 = cartesian.line(series11Mapping);
        series11.name(getString(R.string.right_thigh));
        series11.hovered().markers().enabled(true);
        series11.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series11.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series11.labels(true);

        Line series12 = cartesian.line(series12Mapping);
        series12.name(getString(R.string.left_calf));
        series12.hovered().markers().enabled(true);
        series12.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series12.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series12.labels(true);

        Line series13 = cartesian.line(series13Mapping);
        series13.name(getString(R.string.right_calf));
        series13.hovered().markers().enabled(true);
        series13.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series13.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series13.labels(true);

        Line series14 = cartesian.line(series14Mapping);
        series14.name(getString(R.string.weight));
        series14.hovered().markers().enabled(true);
        series14.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series14.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series14.labels(true);

        Line series15 = cartesian.line(series15Mapping);
        series15.name(getString(R.string.body_fat_string));
        series15.hovered().markers().enabled(true);
        series15.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series15.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        series15.labels(true);


        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        mChartView.setChart(cartesian);
    }

    private int getCalendarWeek(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    //------------Data Entry Classes------------//

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number neck, Number chest, Number shoulder, Number leftArm, Number rightArm, Number leftForearm, Number rightForearm, Number waist, Number hips, Number leftLeg, Number rightLeg, Number leftCalf, Number rightCalf, Number weight, Number bodyFat) {
            super(x, neck);
            setValue("chest", chest);
            setValue("shoulder", shoulder);
            setValue("leftArm", leftArm);
            setValue("rightArm", rightArm);
            setValue("leftForearm", leftForearm);
            setValue("rightForearm", rightForearm);
            setValue("waist", waist);
            setValue("hips", hips);
            setValue("leftLeg", leftLeg);
            setValue("rightLeg", rightLeg);
            setValue("leftCalf", leftCalf);
            setValue("rightCalf", rightCalf);
            setValue("weight", weight);
            setValue("bodyFat", bodyFat);
        }
    }

    private class CustomSingleDataEntry extends ValueDataEntry {

        CustomSingleDataEntry(String x, Number value) {
            super(x, value);
        }
    }
}
