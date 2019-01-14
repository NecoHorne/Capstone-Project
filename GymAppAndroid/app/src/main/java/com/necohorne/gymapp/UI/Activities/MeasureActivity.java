package com.necohorne.gymapp.UI.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.necohorne.gymapp.Models.Measurement;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Calculators;
import com.necohorne.gymapp.Utils.Constants;

import java.util.Date;

public class MeasureActivity extends AppCompatActivity {

    public static final String TAG = MeasureActivity.class.getSimpleName();

    //UI
    private EditText neckEditText;
    private EditText chestEditText;
    private EditText leftArmEditText;
    private EditText rightArmEditText;
    private EditText leftForearmEditText;
    private EditText rightForearmEditText;
    private EditText waistEditText;
    private EditText leftLegEditText;
    private EditText rightLegEditText;
    private EditText leftCalfEditText;
    private EditText rightCalfEditText;
    private EditText weightEditText;

    //Data
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        initUI();
        mSharedPreferences = getSharedPreferences(Constants.PREFS,0);
        checkPrefs();
    }

    //Add Program UI Elements
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.measure_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.measure_menu_save) {
            saveMeasurements();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        neckEditText = findViewById(R.id.neck_et);
        chestEditText = findViewById(R.id.chest_et);
        leftArmEditText = findViewById(R.id.left_arm_et);
        rightArmEditText = findViewById(R.id.right_arm_et);
        leftForearmEditText = findViewById(R.id.left_forearm_et);
        rightForearmEditText = findViewById(R.id.right_forearm_et);
        waistEditText = findViewById(R.id.waist_et);
        leftLegEditText = findViewById(R.id.left_leg_et);
        rightLegEditText = findViewById(R.id.right_leg_et);
        leftCalfEditText = findViewById(R.id.left_calf_et);
        rightCalfEditText = findViewById(R.id.right_calf_et);
        weightEditText = findViewById(R.id.weight_et);
    }

    //Data Handling Methods
    private void checkPrefs() {
        //TODO
    }

    private Measurement getMeasurements(){

        //Create a new Measurement object
        Measurement measurement = new Measurement();

        //set to date of measurement added
        measurement.setDate(new Date());

        //retrieve measurement data from edit texts. if edit text is empty make measurement 0;
        String neckString = neckEditText.getText().toString();
        double neck = (!neckString.equals("")) ? Double.parseDouble(neckString) : 0;
        measurement.setNeck(neck);

        String chestString = chestEditText.getText().toString();
        double chest = (!chestString.equals("")) ? Double.parseDouble(chestString) : 0;
        measurement.setChest(chest);

        String leftArmString = leftArmEditText.getText().toString();
        double leftArm = (!leftArmString.equals("")) ? Double.parseDouble(leftArmString) : 0;
        measurement.setLeftArm(leftArm);

        String rightArmString = rightArmEditText.getText().toString();
        double rightArm = (!rightArmString.equals("")) ? Double.parseDouble(rightArmString) : 0;
        measurement.setRightArm(rightArm);

        String leftForearmString = leftForearmEditText.getText().toString();
        double leftForearm = (!leftForearmString.equals("")) ? Double.parseDouble(leftForearmString) : 0;
        measurement.setLeftForearm(leftForearm);

        String rightForearmString = rightForearmEditText.getText().toString();
        double rightForearm = (!rightForearmString.equals("")) ? Double.parseDouble(rightForearmString) : 0;
        measurement.setRightForearm(rightForearm);

        String waistString = waistEditText.getText().toString();
        double waist = (!waistString.equals("")) ? Double.parseDouble(waistString) : 0;
        measurement.setWaist(waist);

        String leftLegString = leftLegEditText.getText().toString();
        double leftLeg = (!leftLegString.equals("")) ? Double.parseDouble(leftLegString) : 0;
        measurement.setLeftLeg(leftLeg);

        String rightLegString = rightLegEditText.getText().toString();
        double rightLeg = (!rightLegString.equals("")) ? Double.parseDouble(rightLegString) : 0;
        measurement.setRightLeg(rightLeg);

        String leftCalfString = leftCalfEditText.getText().toString();
        double leftCalf = (!leftCalfString.equals("")) ? Double.parseDouble(leftCalfString) : 0;
        measurement.setLeftCalf(leftCalf);

        String rightCalfString = rightCalfEditText.getText().toString();
        double rightCalf = (!rightCalfString.equals("")) ? Double.parseDouble(rightCalfString) : 0;
        measurement.setRightCalf(rightCalf);

        String weightString = weightEditText.getText().toString();
        double weight = (!weightString.equals("")) ? Double.parseDouble(weightString) : 0;
        measurement.setWeight(weight);

        //below variables are should be retrieved from the Shared Preferences.
        int age = mSharedPreferences.getInt(Constants.AGE, 0);
        measurement.setAge(age);
        boolean male = mSharedPreferences.getBoolean(Constants.SEX, true);
        measurement.setMale(male);
        double activityLevel = mSharedPreferences.getFloat(Constants.ACTIVITY, 1);
        measurement.setActivityLevel(activityLevel);
        double height = mSharedPreferences.getFloat(Constants.HEIGHT,0);
        measurement.setHeight(height);


        //if weight is more than 0 use the Calculators class to calculate the rest of the measurement variables
        if(weight > 0){
            measurement.setRestingEnergyExpenditure(Calculators.getRestingEnergyExpenditure(weight, height, age, male));
            measurement.setTotalDailyEnergyExpenditure(Calculators.getTotalDailyEnergyExpenditure(measurement.getRestingEnergyExpenditure(), activityLevel));
            measurement.setProteinAmount(Calculators.getProteinAmount(weight));
            measurement.setFatAmount(Calculators.getFatAmount(measurement.getTotalDailyEnergyExpenditure()));
            measurement.setCarbAmount(Calculators.getCarbAmount(measurement.getProteinAmount(), measurement.getFatAmount(), measurement.getTotalDailyEnergyExpenditure()));
            measurement.setBMI(Calculators.getBMI(height, weight, true));
            measurement.setBMICategory(Calculators.getBMICatagory(measurement.getBMI()));
            measurement.setWaistToHeightRatio(Calculators.waistToHeightRatio(waist, height));
            measurement.setWITHCategory(Calculators.getWTHCatagory(measurement.getWaistToHeightRatio(), male));
        }

        return measurement;
    }

    private void saveMeasurements() {

        Measurement measurement = getMeasurements();
        //TODO Save measurement object to database
        Log.d(TAG, "saveMeasurements: " + measurement.toString());


    }


}