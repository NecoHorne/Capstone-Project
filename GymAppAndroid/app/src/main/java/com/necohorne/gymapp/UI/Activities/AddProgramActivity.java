package com.necohorne.gymapp.UI.Activities;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.necohorne.gymapp.Models.BlockExercise;
import com.necohorne.gymapp.Models.Exercise;
import com.necohorne.gymapp.Models.MuscleSet;
import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.ExerciseDatabaseAdapter;
import com.necohorne.gymapp.Utils.Data.ProgramDatabase;
import com.necohorne.gymapp.Utils.RecyclerAdaptors.AddProgramRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddProgramActivity extends AppCompatActivity {

    private static String TAG = AddProgramActivity.class.getSimpleName();

    //UI Objects
    private Spinner daySpinner;
    private Spinner muscleSpinner;
    private Spinner exerciseSpinner;
    private RecyclerView mRecyclerView;
    private EditText setsEditText;
    private EditText repsEditText;
    private ProgressBar mProgressBar;

    //Data Objects
    private String daySelected;
    private String currentMuscle;
    private ArrayList<Exercise> mExerciseArrayList;
    private ArrayList<BlockExercise> mBlockExerciseArrayList;
    private Exercise mExercise;
    private AddProgramRecyclerAdapter mAdapter;
    public ProgramDatabase mDatabase;
    private Program mProgram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        initUi();
        mBlockExerciseArrayList = new ArrayList<>();
        mProgram = new Program();
        mDatabase = ProgramDatabase.getInstance(getApplicationContext());
        setUpRecycler();
        setDaySpinner();
        setMuscleSpinner();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    //Add Program UI Elements
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_program_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_program_menu_save) {
            saveProgram();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        mProgressBar = findViewById(R.id.add_program_progress);
        daySpinner = findViewById(R.id.day_spinner);
        muscleSpinner = findViewById(R.id.muscle_spinner);
        exerciseSpinner = findViewById(R.id.exercise_spinner);
        mRecyclerView = findViewById(R.id.add_program_recycler);

        repsEditText = findViewById(R.id.reps_edit_text);
        setsEditText = findViewById(R.id.sets_edit_text);

        Button addExerciseButton = findViewById(R.id.add_exercise_button);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExerciseToList();
            }
        });
    }

    private void setUpRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new AddProgramRecyclerAdapter(getApplicationContext(), mBlockExerciseArrayList);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDaySpinner(){
        //this method sets up the data for the mDay spinner
        ArrayAdapter<CharSequence> muscleAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.days, R.layout.spinner_item );
        muscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(muscleAdapter);

        //check if the intent that launched the activity has a Specific day set from another activity, if so set the spinner to that day.
        if(getIntent().hasExtra(Constants.DAY)){
            String intentDay = getIntent().getStringExtra(Constants.DAY);
            daySpinner.setSelection(getIndex(daySpinner, intentDay));
        }

        daySpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daySelected = daySpinner.getSelectedItem().toString();
                new CheckDatabase().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //not used
            }
        } );
    }

    private int getIndex(Spinner spinner, String day){
        //get the position of the day in the spinner
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(day)){
                index = i;
            }
        }
        return index;
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
                //not used
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
                //not used
            }
        } );

    }

    //Add Program Methods
    private void addExerciseToList() {

        String sets = setsEditText.getText().toString();
        String numReps = repsEditText.getText().toString();
        BlockExercise blockExercise = new BlockExercise();

        if(!sets.equals("") && !numReps.equals("")){
            int numSets = Integer.parseInt(sets);
            int repsInt = Integer.parseInt(numReps);

            if(numSets > 0 && repsInt > 0){
                String[] reps = new String[numSets];
                for(int i = 0; i < reps.length; i++) {
                    reps[i] = numReps;
                }
                blockExercise.setExercise(mExercise);
                blockExercise.setNumberSets(numSets);
                blockExercise.setRepsPerSet(reps);
                mBlockExerciseArrayList.add(blockExercise);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), getString(R.string.toast_exercise_added), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_sets_reps_null), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_sets_reps_null), Toast.LENGTH_LONG).show();
        }
    }

    private void saveProgram() {
        //create an empty array list of muscle sets
        //create an empty array to store the sorted exercises.
        //sort the muscles into a unique set to make creation of the muscle sets easier.
        //for each muscle in the unique muscle set check the block exercise list for exercises of that muscle
        //go through each BlockExercise in the array list
        //create a new MuscleSet for each unique muscle in the set
        //if the muscle corresponds to an exercise of that muscle add it to the sorted exercise list
        //add the sorted exercises to the MuscleSet
        //add each unique muscle set to the MuscleSet array list
        //add muscle set array list to the program object
        //after saving program end the activity and return to main.
        ArrayList<MuscleSet> muscleSets = new ArrayList<>();

        List<String> muscleList = new ArrayList<>();
        for(int i = 0; i < mBlockExerciseArrayList.size(); i++) {
            muscleList.add(mBlockExerciseArrayList.get(i).getExercise().getMuscleTargeted());
        }

        Set<String> uniqueMuscles = new HashSet<>(muscleList);
        for(String muscle : uniqueMuscles) {
            ArrayList<BlockExercise> sortedExerciseList = new ArrayList<>();
            MuscleSet muscleSet = new MuscleSet();
            muscleSet.setMuscleName(muscle);
            for(int i = 0; i < mBlockExerciseArrayList.size(); i++) {
                if(mBlockExerciseArrayList.get(i).getExercise().getMuscleTargeted().equals(muscle)){
                    sortedExerciseList.add(mBlockExerciseArrayList.get(i));
                }
                muscleSet.setExercises(sortedExerciseList);
            }
            muscleSets.add(muscleSet);
        }
        mProgram.setDay(daySelected);

        mProgram.setSets(muscleSets);
//        Log.d(TAG, "saveProgram: " + mProgram.toString());
        new DataBaseOperation().execute(mProgram);

        finish();
    }

    //------------ASYNC TASKS------------//
    public class CheckDatabase extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            if(mDatabase.ProgramDao().searchProgramForDay(daySelected) != null){
                mProgram = mDatabase.ProgramDao().searchProgramForDay(daySelected);
//                Log.d(TAG, "doInBackground: " + mProgram.toString());
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                mBlockExerciseArrayList.clear();
                ArrayList<MuscleSet> sets = mProgram.getSets();
                for(int i = 0; i < sets.size(); i++) {
                    MuscleSet set = sets.get(i);
                    ArrayList<BlockExercise> exercises = set.getExercises();
                    mBlockExerciseArrayList.addAll(exercises);
                }
                mAdapter.notifyDataSetChanged();
            }else {
                //when the spinner moves to a day which has no associated program clear the list and create a new program.
                mBlockExerciseArrayList.clear();
                mProgram = new Program();
                mAdapter.notifyDataSetChanged();
            }
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public class DataBaseOperation extends AsyncTask<Program, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Program... programs) {

            Program program = programs[0];

            if(mDatabase.ProgramDao().searchProgramForDay(program.getDay()) != null){
                mDatabase.ProgramDao().updateProgram(program);
                return false;
            } else {
                mDatabase.ProgramDao().insertProgram(program);
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if(bool){
                Toast.makeText(getApplicationContext(), "Program for " + daySelected + " saved.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Program for "+ daySelected +" Updated!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
