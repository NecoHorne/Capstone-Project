package com.necohorne.gymapp.UI.Dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Calculators;
import com.necohorne.gymapp.Utils.Constants;

public class BasicInfoDialog extends DialogFragment {

    private Context mContext;
    private View mView;
    private SharedPreferences mSharedPreferences;

    private EditText mAge;
    private EditText mHeight;
    private Switch mSexSwitch;
    private TextView mSexTv;
    private SeekBar mActivitySeekBar;
    private TextView mActivityTv;
    private Button mSaveButton;

    private String mActivityLevel;
    private boolean mSex;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.basic_info_dialog, container, false);
        mContext = getActivity();

        mSharedPreferences = mContext.getSharedPreferences(Constants.PREFS,0);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        getDialog().setCancelable(false);

        initUI();
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void initUI(){
        mAge = mView.findViewById(R.id.dialog_age_et);
        mHeight = mView.findViewById(R.id.dialog_height_et);
        mSexSwitch = mView.findViewById(R.id.dialog_sex_switch);
        mSex = mSexSwitch.isChecked();
        mSexSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSex = isChecked;
                if(isChecked){
                    mSexTv.setText(mContext.getString(R.string.male));
                } else {
                    mSexTv.setText(mContext.getString(R.string.female));
                }
            }
        });
        mSexTv = mView.findViewById(R.id.dialog_sex_tv);
        if(mSex){
            mSexTv.setText(mContext.getString(R.string.male));
        } else {
            mSexTv.setText(mContext.getString(R.string.female));
        }

        mActivitySeekBar = mView.findViewById(R.id.dialog_activity_level_seekbar);
        mActivityTv = mView.findViewById(R.id.dialog_activity_level_tv);
        mActivitySeekBar.setProgress(0);
        mActivityTv.setText(mContext.getString(R.string.sedentary));
        mActivitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress){
                    case 0:
                        mActivityTv.setText(mContext.getString(R.string.sedentary));
                        mActivityLevel = mContext.getString(R.string.sedentary);
                        break;
                    case 1:
                        mActivityTv.setText(mContext.getString(R.string.light));
                        mActivityLevel =mContext.getString(R.string.light);
                        break;
                    case 2:
                        mActivityTv.setText(mContext.getString(R.string.moderate));
                        mActivityLevel = mContext.getString(R.string.moderate);
                        break;
                    case 3:
                        mActivityTv.setText(mContext.getString(R.string.very_active));
                        mActivityLevel = mContext.getString(R.string.very_active);
                        break;
                    case 4:
                        mActivityTv.setText(mContext.getString(R.string.extremely_active));
                        mActivityLevel = mContext.getString(R.string.extremely_active);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSaveButton = mView.findViewById(R.id.dialog_save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

    }

    private void saveInfo() {
        String ageString = mAge.getText().toString();
        String heightString = mHeight.getText().toString();

        if(!ageString.equals("") && !heightString.equals("")){
            int age = Integer.parseInt(ageString);
            float height = Float.parseFloat(heightString);

            if(age > 0 && height > 0){
                save(age, height);
            } else {
                Toast.makeText(mContext, "Age and Height cannot be 0", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(mContext, "Age and Height cannot be left blank", Toast.LENGTH_LONG).show();
        }
    }

    private void save(int age, float height){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.AGE, age);
        editor.putFloat(Constants.HEIGHT, height);
        editor.putBoolean(Constants.SEX, mSex);
        editor.putString(Constants.ACTIVITY, mActivityLevel);
        editor.commit();
        getDialog().dismiss();
        Toast.makeText(mContext, "Basic info saved!", Toast.LENGTH_LONG).show();
    }
}
