package com.necohorne.gymapp.Utils.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.necohorne.gymapp.Models.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.necohorne.gymapp.Utils.Constants.EXERCISE_EQUIPMENT;
import static com.necohorne.gymapp.Utils.Constants.EXERCISE_INSTRUCTIONS;
import static com.necohorne.gymapp.Utils.Constants.EXERCISE_MUSCLE;
import static com.necohorne.gymapp.Utils.Constants.EXERCISE_NAME;
import static com.necohorne.gymapp.Utils.Constants.EXERCISE_TYPE;
import static com.necohorne.gymapp.Utils.Constants.TABLE_NAME;

public class ExerciseDatabaseAdapter {

    protected static final String TAG = ExerciseDatabaseAdapter.class.getSimpleName();

    private final Context mContext;
    private SQLiteDatabase mDb;
    private ExerciseDataHandler mDbHelper;
//    private String[] columns = {EXERCISE_NAME, EXERCISE_MUSCLE, EXERCISE_EQUIPMENT, EXERCISE_TYPE, EXERCISE_INSTRUCTIONS };

    public ExerciseDatabaseAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new ExerciseDataHandler(mContext);
    }

    public ExerciseDatabaseAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public ExerciseDatabaseAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getTestData()
    {
        try
        {
            String sql ="SELECT * FROM "+TABLE_NAME +" ";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getMuscleData(String muscle)
    {
        try
        {
            String sql = "SELECT * FROM " +
                    ""+TABLE_NAME+" WHERE "+EXERCISE_MUSCLE+"='"+muscle+"'";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public static ArrayList<Exercise> getExercisesFromDb(Cursor cursor){

        ArrayList<Exercise> exercises = new ArrayList<>();
        if(!exercises.isEmpty()){
            exercises.clear();
        }else {
            while(cursor.moveToNext()){
                Exercise exercise = new Exercise();

                //Instructions string data in DB are saved as a JSON Object, JSON needs to be parsed to get each individual instruction.
                String instructions = cursor.getString(cursor.getColumnIndex(EXERCISE_INSTRUCTIONS));
                exercise.setInstructions(getInstructions(instructions));
                exercise.setName(cursor.getString(cursor.getColumnIndex(EXERCISE_NAME)));
                exercise.setExerciseType(cursor.getString(cursor.getColumnIndex(EXERCISE_TYPE)));
                exercise.setMuscleTargeted(cursor.getString(cursor.getColumnIndex(EXERCISE_MUSCLE)));
                exercise.setEquipmentType(cursor.getString(cursor.getColumnIndex(EXERCISE_EQUIPMENT)));
                exercises.add(exercise);
            }
        }
        return exercises;
    }

    private static ArrayList<String> getInstructions(String instructionString){
        ArrayList<String> stringArrayList = new ArrayList<>();
        try {
            JSONObject instructionJSON = new JSONObject(instructionString);
            JSONArray jsonArray = instructionJSON.optJSONArray(EXERCISE_INSTRUCTIONS);
            for(int i = 0; i < jsonArray.length(); i++){
                stringArrayList.add(jsonArray.optString(i));
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        return stringArrayList;
    }

}

