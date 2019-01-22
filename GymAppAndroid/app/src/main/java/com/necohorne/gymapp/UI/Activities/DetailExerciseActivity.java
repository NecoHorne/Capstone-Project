package com.necohorne.gymapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.necohorne.gymapp.Models.Exercise;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Constants;
import com.squareup.picasso.Picasso;

public class DetailExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_exercise);
        Exercise exercise = getExerciseFromIntent();
        if(exercise != null){
            initUI(exercise);
        }else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_error_occured), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initUI(Exercise exercise){
        ImageView imageView = findViewById(R.id.detail_imageview);
        String url = getImageUrl(exercise);

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

        setTitle(exercise.getName());

        TextView muscleTv = findViewById(R.id.exercise_detail_muscle_tv);
        muscleTv.setText(exercise.getMuscleTargeted());

        TextView equipmentTv = findViewById(R.id.exercise_detail_equip_tv);
        equipmentTv.setText(exercise.getEquipmentType());

        TextView typeTv = findViewById(R.id.exercise_detail_type_tv);
        typeTv.setText(exercise.getExerciseType());

        TextView instructionsTv = findViewById(R.id.exercise_detail_intructions_tv);

        if(exercise.getInstructions() != null){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < exercise.getInstructions().size(); i++) {
                stringBuilder.append(i+1);
                stringBuilder.append(": ");
                stringBuilder.append(exercise.getInstructions().get(i));
                stringBuilder.append("\n\n");
            }
            instructionsTv.setText(stringBuilder.toString());
        }
    }

    private Exercise getExerciseFromIntent(){
        Bundle data = getIntent().getExtras();
        if(data != null) {
            return data.getParcelable(Constants.INTENT_EXERCISE);
        }
        return null;
    }

    private String getImageUrl(Exercise exercise) {

        String muscle = exercise.getMuscleTargeted();

        switch(muscle){
            case Constants.ABDUCTORS:
                return Constants.ABDUCTORS_URL;
            case Constants.ABS:
                return Constants.ABS_URL;
            case Constants.ADDUCTOR:
                return Constants.ADDUCTOR_URL;
            case Constants.BICEPS:
                return Constants.BICEPS_URL;
            case Constants.CALVES:
                return Constants.CALVES_URL;
            case Constants.CHEST:
                return Constants.CHEST_URL;
            case Constants.FOREARMS:
                return Constants.FOREARMS_URL;
            case Constants.GLUTES:
                return Constants.GLUTES_URL;
            case Constants.HAMSTRINGS:
                return Constants.HAMSTRINGS_URL;
            case Constants.LATS:
                return Constants.LATS_URL;
            case Constants.LOWER_BACK:
                return Constants.LOWER_BACK_URL;
            case Constants.MIDDLE_BACK:
                return Constants.MIDDLE_BACK_URL;
            case Constants.NECK:
                return Constants.NECK_URL;
            case Constants.QUADS:
                return Constants.QUADS_URL;
            case Constants.SHOULDERS:
                return Constants.SHOULDERS_URL;
            case Constants.TRAPS:
                return Constants.TRAPS_URL;
            case Constants.TRICEPS:
                return Constants.TRICEPS_URL;
        }

        return null;
    }
}
