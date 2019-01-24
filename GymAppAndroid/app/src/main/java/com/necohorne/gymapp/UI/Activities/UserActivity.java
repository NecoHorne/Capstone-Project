package com.necohorne.gymapp.UI.Activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.necohorne.gymapp.Models.Measurement;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Calculators;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.MeasurementsDatabase;

public class UserActivity extends AppCompatActivity {

    private TextView userActivityLevelTv;
    private TextView userWeightTv;
    private TextView userBmiTv;
    private TextView userBmiCatTv;
    private TextView userWeightTHCatTv;
    private TextView userREETv;
    private TextView userTDEETv;
    private TextView maintainCalsTv;
    private TextView loseCalsTv;
    private TextView gainCalsTv;
    private TextView maintainFatTv;
    private TextView loseFatTv;
    private TextView gainFatTv;
    private TextView maintainProteinTv;
    private TextView loseProteinTv;
    private TextView gainProteinTv;
    private TextView maintainCarbsTv;
    private TextView loseCarbsTv;
    private TextView gainCarbsTv;
    private TextView userAgeTv;
    private TextView userHeightTv;
    private TextView userSexTv;

    public MeasurementsDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initUI();
        mDatabase = MeasurementsDatabase.getInstance(UserActivity.this);
        new UpdateUserStats().execute();
        setTitle(getString(R.string.my_stats));
    }

    private void initUI() {
        userActivityLevelTv = findViewById(R.id.user_activity_level_tv);
        userWeightTv = findViewById(R.id.user_weight_tv);
        userBmiTv = findViewById(R.id.bmi_tv);
        userBmiCatTv = findViewById(R.id.bmi_cat_tv);
        userWeightTHCatTv = findViewById(R.id.waist_to_height_cat);
        userREETv = findViewById(R.id.ree_tv);
        userTDEETv = findViewById(R.id.tdee_tv);
        maintainCalsTv = findViewById(R.id.maintain_cals_tv);
        loseCalsTv = findViewById(R.id.lose_cals_tv);
        gainCalsTv = findViewById(R.id.gain_cals_tv);
        maintainFatTv = findViewById(R.id.maintain_fat_tv);
        loseFatTv = findViewById(R.id.lose_fat_tv);
        gainFatTv = findViewById(R.id.gain_fat_tv);
        maintainProteinTv = findViewById(R.id.maintain_protein_tv);
        loseProteinTv = findViewById(R.id.lose_protein_tv);
        gainProteinTv = findViewById(R.id.gain_protein_tv);
        maintainCarbsTv = findViewById(R.id.textView15);
        loseCarbsTv = findViewById(R.id.lose_carbs_tv);
        gainCarbsTv = findViewById(R.id.gain_carbs_tv);
        userAgeTv = findViewById(R.id.basic_age_tv);
        userHeightTv = findViewById(R.id.basic_height_tv);
        userSexTv = findViewById(R.id.basic_sex_tv);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public class UpdateUserStats extends AsyncTask<Void, Void, Measurement>{

        @Override
        protected Measurement doInBackground(Void... voids) {
            //only use the latest measurement added in the db to update user stats.
            return mDatabase.MeasurementDao().lastMeasurement();
        }

        @Override
        protected void onPostExecute(Measurement measurement) {

            SharedPreferences prefs = getSharedPreferences(Constants.PREFS,0);
            boolean male = prefs.getBoolean(Constants.SEX, true);
            String activityLevel = prefs.getString(Constants.ACTIVITY,"");

            if(activityLevel.equals(getString(R.string.sedentary))){
                userActivityLevelTv.setText(getString(R.string.sedentary));
            } else if(activityLevel.equals(getString(R.string.light))){
                userActivityLevelTv.setText(getString(R.string.light));
            }else if(activityLevel.equals(getString(R.string.moderate))){
                userActivityLevelTv.setText(getString(R.string.moderate));
            }else if(activityLevel.equals(getString(R.string.very_active))){
                userActivityLevelTv.setText(getString(R.string.very_active));
            }else if(activityLevel.equals(getString(R.string.extremely_active))){
                userActivityLevelTv.setText(getString(R.string.extremely_active));
            }

            double loseCarbs = Calculators.getCarbAmount(measurement.getProteinAmount(), measurement.getFatAmount(), (measurement.getTotalDailyEnergyExpenditure() - 500));
            double gainCarbs = Calculators.getCarbAmount(measurement.getProteinAmount(), measurement.getFatAmount(), (measurement.getTotalDailyEnergyExpenditure() + 500));

            String weight = String.valueOf(Math.round(measurement.getWeight())) + getString(R.string.kilogram);
            String bmi = String.valueOf(Math.round(measurement.getBMI()));
            String bmiCat = measurement.getBMICategory();
            String weightTHCat = measurement.getWITHCategory();
            String ree = String.valueOf(round(measurement.getRestingEnergyExpenditure(), 1) ) + getString(R.string.kilo_cals);
            String tdee = String.valueOf(round(measurement.getTotalDailyEnergyExpenditure(),1) ) + getString(R.string.kilo_cals);
            String maintainCals = String.valueOf(round(measurement.getTotalDailyEnergyExpenditure(),1) ) + getString(R.string.kilo_cals);
            String loseCals = String.valueOf(round(measurement.getTotalDailyEnergyExpenditure(), 1) - 500) + getString(R.string.kilo_cals);
            String gainCals = String.valueOf(round(measurement.getTotalDailyEnergyExpenditure(), 1)  + 500) + getString(R.string.kilo_cals);
            String maintainFat = String.valueOf(round(measurement.getFatAmount() ,1)) + getString(R.string.fat);
            String maintainProtein = String.valueOf(round(measurement.getProteinAmount(), 1)) + getString(R.string.protein);
            String maintainCarbs = String.valueOf(round(measurement.getCarbAmount(), 1)) + getString(R.string.carbs);
            String loseFat = String.valueOf(round(measurement.getFatAmount(), 1)) + getString(R.string.fat);
            String loseProtein = String.valueOf(round(measurement.getProteinAmount(), 1)) + getString(R.string.protein);
            String loseCarbsString = String.valueOf(round(loseCarbs, 1)) + getString(R.string.carbs);
            String gainFat = String.valueOf(round(measurement.getFatAmount(), 1)) + getString(R.string.fat);
            String gainProtein = String.valueOf(round(measurement.getProteinAmount(), 1)) + getString(R.string.protein);
            String gainCarbsString = String.valueOf(round(gainCarbs, 2)) + getString(R.string.carbs);
            String userHeight = String.valueOf(measurement.getHeight()) + getString(R.string.centimeter);

            userWeightTv.setText(weight);
            userBmiTv.setText(bmi);
            userBmiCatTv.setText(bmiCat);
            userWeightTHCatTv.setText(weightTHCat);
            userREETv.setText(ree);
            userTDEETv.setText(tdee);
            maintainCalsTv.setText(maintainCals);
            loseCalsTv.setText(loseCals);
            gainCalsTv.setText(gainCals);
            maintainFatTv.setText(maintainFat);
            maintainProteinTv.setText(maintainProtein);
            maintainCarbsTv.setText(maintainCarbs);
            loseProteinTv.setText(loseProtein);
            gainProteinTv.setText(gainProtein);
            loseFatTv.setText(loseFat);
            gainFatTv.setText(gainFat);
            loseCarbsTv.setText(loseCarbsString);
            gainCarbsTv.setText(gainCarbsString);
            userAgeTv.setText(String.valueOf(measurement.getAge()));
            userHeightTv.setText(userHeight);

            if(male){
                userSexTv.setText(getString(R.string.male));
            }else {
                userSexTv.setText(getString(R.string.female));
            }

        }
    }
}
