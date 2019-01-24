package com.necohorne.gymapp.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.necohorne.gymapp.Models.MuscleSet;
import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.ProgramViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyProgramActivity extends AppCompatActivity {

    public static final String TAG = MyProgramActivity.class.getSimpleName();

    //UI
    private TextView mondayTv;
    private TextView tuesdayTv;
    private TextView wednesdayTv;
    private TextView thursdayTv;
    private TextView fridayTv;
    private TextView saturdayTv;
    private TextView sundayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program);
        initUI();
        engageViewModel();
        setTitle(getString(R.string.my_program));
    }

    private void initUI() {

        final Intent intent = new Intent(MyProgramActivity.this, AddProgramActivity.class);

        mondayTv = findViewById(R.id.myp_monday_tv);
        ImageButton mondayBtn = findViewById(R.id.myp_monday_btn);
        mondayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.MONDAY);
                startActivity(intent);
                finish();
            }
        });

        tuesdayTv = findViewById(R.id.myp_tuesday_tv);
        ImageButton tuesdayBtn = findViewById(R.id.myp_tuesday_btn);
        tuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.TUESDAY);
                startActivity(intent);
                finish();
            }
        });
        wednesdayTv = findViewById(R.id.myp_wednesday_tv);
        ImageButton wednesdayBtn = findViewById(R.id.myp_wednesday_btn);
        wednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.WEDNESDAY);
                startActivity(intent);
                finish();
            }
        });
        thursdayTv = findViewById(R.id.myp_thursday_tv);
        ImageButton thursdayBtn = findViewById(R.id.myp_thursday_btn);
        thursdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.THURSDAY);
                startActivity(intent);
                finish();
            }
        });
        fridayTv = findViewById(R.id.myp_friday_tv);
        ImageButton fridayBtn = findViewById(R.id.myp_friday_btn);
        fridayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.FRIDAY);
                startActivity(intent);
                finish();
            }
        });
        saturdayTv = findViewById(R.id.myp_saturday_tv);
        ImageButton saturdayBtn = findViewById(R.id.myp_saturday_btn);
        saturdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.SATURDAY);
                startActivity(intent);
                finish();
            }
        });
        sundayTv = findViewById(R.id.myp_sunday_tv);
        ImageButton sundayBtn = findViewById(R.id.myp_sunday_btn);
        sundayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DAY, Constants.SUNDAY);
                startActivity(intent);
                finish();
            }
        });
    }

    public void engageViewModel(){
        ProgramViewModel viewModel = ViewModelProviders.of(this).get(ProgramViewModel.class);
        viewModel.getPrograms().observe(this, new Observer<List<Program>>(){
            @Override
            public void onChanged(@Nullable List<Program> programs) {
                ArrayList<Program> programArrayList = (ArrayList<Program>) programs;

                for(int i = 0; i < programArrayList.size(); i++) {
                    String day = programArrayList.get(i).getDay();
                    switch(day){
                        case Constants.MONDAY:
                            mondayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.TUESDAY:
                            tuesdayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.WEDNESDAY:
                            wednesdayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.THURSDAY:
                            thursdayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.FRIDAY:
                            fridayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.SATURDAY:
                            saturdayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                        case Constants.SUNDAY:
                            sundayTv.setText(getMuscles(programArrayList.get(i).getSets()));
                            break;
                    }
                }
            }
        });
    }

    private String getMuscles(ArrayList<MuscleSet> muscleSets){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < muscleSets.size(); j++) {
            sb.append(muscleSets.get(j).getMuscleName());
            if(j != muscleSets.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
