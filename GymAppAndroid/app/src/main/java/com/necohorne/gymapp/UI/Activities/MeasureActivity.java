package com.necohorne.gymapp.UI.Activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.necohorne.gymapp.Models.Measurement;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.UI.Dialog.BasicInfoDialog;
import com.necohorne.gymapp.Utils.Calculators;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.MeasurementsDatabase;

import java.util.Date;

public class MeasureActivity extends AppCompatActivity {

    public static final String TAG = MeasureActivity.class.getSimpleName();

    //UI
    private EditText neckEditText;
    private EditText chestEditText;
    private EditText shoulderEditText;
    private EditText leftArmEditText;
    private EditText rightArmEditText;
    private EditText leftForearmEditText;
    private EditText rightForearmEditText;
    private EditText waistEditText;
    private EditText hipsEditText;
    private EditText leftLegEditText;
    private EditText rightLegEditText;
    private EditText leftCalfEditText;
    private EditText rightCalfEditText;
    private EditText weightEditText;
    private EditText bodyFatEditText;

    //Data
    private SharedPreferences mSharedPreferences;
    private MeasurementsDatabase mDatabase;
    private boolean prefBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        initUI();
        mDatabase = MeasurementsDatabase.getInstance(getApplicationContext());
        mSharedPreferences = getSharedPreferences(Constants.PREFS,0);
        checkPrefs();
        setTitle(getString(R.string.add_measurement));
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
        shoulderEditText = findViewById(R.id.shoulders_et);
        leftArmEditText = findViewById(R.id.left_arm_et);
        rightArmEditText = findViewById(R.id.right_arm_et);
        leftForearmEditText = findViewById(R.id.left_forearm_et);
        rightForearmEditText = findViewById(R.id.right_forearm_et);
        waistEditText = findViewById(R.id.waist_et);
        hipsEditText = findViewById(R.id.hips_et);
        leftLegEditText = findViewById(R.id.left_leg_et);
        rightLegEditText = findViewById(R.id.right_leg_et);
        leftCalfEditText = findViewById(R.id.left_calf_et);
        rightCalfEditText = findViewById(R.id.right_calf_et);
        weightEditText = findViewById(R.id.weight_et);
        bodyFatEditText = findViewById(R.id.body_fat_et);
    }

    //Data Handling Methods
    private void checkPrefs(){
        boolean age = mSharedPreferences.contains( Constants.AGE);
        boolean height = mSharedPreferences.contains( Constants.HEIGHT);
        boolean activity = mSharedPreferences.contains( Constants.ACTIVITY);
        boolean sex = mSharedPreferences.contains( Constants.SEX);
        prefBool = age && height && activity && sex;

        basicInfoDialogPrompt();
    }

    private void basicInfoDialogPrompt() {
        //check if the shared preferences contain the basic info of the user if not prompt the user to add details
        if(!prefBool){
            BasicInfoDialog dialog = new BasicInfoDialog();
            dialog.show(getFragmentManager(), "info_dialog_prompt");
        }
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

        String shoulderString = shoulderEditText.getText().toString();
        double shoulders = (!shoulderString.equals("")) ? Double.parseDouble(shoulderString) : 0;
        measurement.setShoulders(shoulders);

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

        String hipsString = hipsEditText.getText().toString();
        double hips = (!hipsString.equals("")) ? Double.parseDouble(hipsString) : 0;
        measurement.setHips(hips);

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

        String bodyFatString = bodyFatEditText.getText().toString();
        double bodyFat = (!bodyFatString.equals("")) ? Double.parseDouble(bodyFatString) : 0;
        measurement.setBodyFat(bodyFat);

        //below variables are should be retrieved from the Shared Preferences.
        int age = mSharedPreferences.getInt(Constants.AGE, 0);
        measurement.setAge(age);
        boolean male = mSharedPreferences.getBoolean(Constants.SEX, true);
        measurement.setMale(male);
        String activityLevel = mSharedPreferences.getString(Constants.ACTIVITY, "");

        if(activityLevel.equals(getString(R.string.sedentary))){
            measurement.setActivityLevel(Calculators.SEDENTERAY);
        } else if(activityLevel.equals(getString(R.string.light))){
            measurement.setActivityLevel(Calculators.LIGHT);
        }else if(activityLevel.equals(getString(R.string.moderate))){
            measurement.setActivityLevel(Calculators.MODERATE);
        }else if(activityLevel.equals(getString(R.string.very_active))){
            measurement.setActivityLevel(Calculators.VERY_ACTIVE);
        }else if(activityLevel.equals(getString(R.string.extremely_active))){
            measurement.setActivityLevel(Calculators.EXTREMELY_ACTIVE);
        }

        double height = mSharedPreferences.getFloat(Constants.HEIGHT,0);
        measurement.setHeight(height);

        //if weight is more than 0 use the Calculators class to calculate the rest of the measurement variables
        if(weight > 0){
            measurement.setRestingEnergyExpenditure(Calculators.getRestingEnergyExpenditure(weight, height, age, male));
            measurement.setTotalDailyEnergyExpenditure(Calculators.getTotalDailyEnergyExpenditure(measurement.getRestingEnergyExpenditure(), measurement.getActivityLevel()));
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
        if(prefBool){
            Measurement measurement = getMeasurements();
            if(measurement.getWeight() <= 0 || measurement.getWaist() <=0){
                Toast.makeText(getApplicationContext(), getString(R.string.measure_toast_message), Toast.LENGTH_LONG).show();
            } else {
                new SaveToDatabase().execute(measurement);
                finish();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.measure_toast_error), Toast.LENGTH_LONG).show();
            basicInfoDialogPrompt();
        }
    }

    public class SaveToDatabase extends AsyncTask<Measurement, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Measurement... measurements) {
            Measurement measurement = measurements[0];
            mDatabase.MeasurementDao().insertMeasurement(measurement);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(getApplicationContext(), "Measurement Saved.", Toast.LENGTH_LONG).show();
        }
    }
}
