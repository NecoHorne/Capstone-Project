package com.necohorne.gymapp.UI.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.necohorne.gymapp.Models.Exercise;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.ExerciseDatabaseAdapter;

import java.util.ArrayList;

public class AddProgramActivity extends AppCompatActivity {

    private static String TAG = AddProgramActivity.class.getSimpleName();

    private Spinner daySpinner;
    private Spinner muscleSpinner;
    private Spinner exerciseSpinner;
    private RecyclerView mRecyclerView;
    private Button addExerciseButton;
    private Button saveButton;

    //Data Objects
    private String daySelected;
    private String currentMuscle;
    private String currentExercise;
    private EditText setsEditText;
    private EditText repsEditText;
    private ArrayList<Exercise> mExerciseArrayList;
    private Exercise mExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        initUi();
        setDaySpinner();
        setMuscleSpinner();
    }

    private void initUi() {
        daySpinner = findViewById(R.id.day_spinner);
        muscleSpinner = findViewById(R.id.muscle_spinner);
        exerciseSpinner = findViewById(R.id.exercise_spinner);
        mRecyclerView = findViewById(R.id.add_program_recycler);
        repsEditText = findViewById(R.id.sets_edit_text);
        setsEditText = findViewById(R.id.reps_edit_text);
        addExerciseButton = findViewById(R.id.add_exercise_button);
        saveButton = findViewById(R.id.program_save_btn);
    }

    private void setDaySpinner(){
        //this method sets up the data for the day spinner
        ArrayAdapter<CharSequence> muscleAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.days, R.layout.spinner_item );
        muscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(muscleAdapter);
        daySpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daySelected = daySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }

    private void setMuscleSpinner(){
        //this method sets up the data for the muscle spinner
        ArrayAdapter<CharSequence> muscleAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.muscles, R.layout.spinner_item );
        muscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscleSpinner.setAdapter(muscleAdapter);
        muscleSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //get the current muscle selected
                currentMuscle = muscleSpinner.getSelectedItem().toString();

                //open data base
                ExerciseDatabaseAdapter databaseAdapter = new ExerciseDatabaseAdapter(getApplicationContext());
                databaseAdapter.createDatabase();
                databaseAdapter.open();

                //search the exercises that correspond to selected muscle
                Cursor dataBaseCursor = databaseAdapter.getMuscleData(currentMuscle);
                mExerciseArrayList = new ArrayList<>();
                mExerciseArrayList = ExerciseDatabaseAdapter.getExercisesFromDb(dataBaseCursor);

                //set exercise list for currently selected muscle
                setExerciseSpinner(mExerciseArrayList);

                //close db connection
                databaseAdapter.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }

    private void setExerciseSpinner(final ArrayList<Exercise> exerciseArrayList) {
        //this method sets up the data for the muscle spinner

        //create an adapter from the exercise arrayList
        ArrayAdapter<Exercise> exerciseAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, exerciseArrayList);
        exerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(exerciseAdapter);
        exerciseSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //get the currently selected exercise
                mExercise = exerciseArrayList.get(position);
//                Log.d(TAG, "current exercise selected: " + mExercise.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

    }
}
