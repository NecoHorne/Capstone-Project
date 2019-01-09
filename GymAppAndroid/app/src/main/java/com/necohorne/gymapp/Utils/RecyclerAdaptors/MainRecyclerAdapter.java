package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.necohorne.gymapp.Models.MuscleSet;
import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private static final String TAG = MainRecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private Program mProgram;

    public MainRecyclerAdapter(Context context, Program program) {
        mContext = context;
        mProgram = program;
    }

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.muscle_set_list_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder viewHolder, int i) {
        //get the list of sets from the program
        ArrayList<MuscleSet> muscleSetArrayList = mProgram.getSets();
        //get the current set from the list of sets;
        MuscleSet muscleSet = muscleSetArrayList.get(i);
        //get the muscle name
        viewHolder.muscleTextView.setText(muscleSet.getMuscleName());

        //Nested recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        MuscleSetRecyclerAdapter adapter = new MuscleSetRecyclerAdapter(mContext, muscleSet.getExercises());
        viewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.mRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mProgram.getSets().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerView;
        TextView muscleTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            muscleTextView = itemView.findViewById(R.id.muscle_tv);
            mRecyclerView = itemView.findViewById(R.id.muscle_set_recyclerview);
        }
    }
}
