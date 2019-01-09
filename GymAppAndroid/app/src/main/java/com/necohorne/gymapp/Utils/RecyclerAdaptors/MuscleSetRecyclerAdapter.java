package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.necohorne.gymapp.Models.BlockExercise;
import com.necohorne.gymapp.Models.Exercise;
import com.necohorne.gymapp.R;

import java.util.ArrayList;

public class MuscleSetRecyclerAdapter extends RecyclerView.Adapter<MuscleSetRecyclerAdapter.ViewHolder> {

    private static final String TAG = MuscleSetRecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<BlockExercise> mBlockExercises;

    public MuscleSetRecyclerAdapter(Context context, ArrayList<BlockExercise> blockExercises) {
        mContext = context;
        mBlockExercises = blockExercises;
    }

    @NonNull
    @Override
    public MuscleSetRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_block_list_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleSetRecyclerAdapter.ViewHolder viewHolder, int i) {
        Exercise exercise = mBlockExercises.get(i).getExercise();
        viewHolder.exerciseName.setText(exercise.getName());

        //Nested recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        ExerciseBlockRecyclerAdapter adapter = new ExerciseBlockRecyclerAdapter(mContext, mBlockExercises.get(i));
        viewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.mRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mBlockExercises.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerView;
        TextView exerciseName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.exercise_block_recyclerview);
            exerciseName = itemView.findViewById(R.id.exercise_name_tv);
        }
    }
}
