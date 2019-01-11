package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.necohorne.gymapp.Models.BlockExercise;
import com.necohorne.gymapp.R;

import java.util.ArrayList;

public class AddProgramRecyclerAdapter extends RecyclerView.Adapter<AddProgramRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<BlockExercise> mBlockExercises;

    public AddProgramRecyclerAdapter(Context context, ArrayList<BlockExercise> blockExercises) {
        mContext = context;
        mBlockExercises = blockExercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_program_list_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final BlockExercise blockExercise = mBlockExercises.get(i);
        viewHolder.exerciseTitle.setText(blockExercise.getExercise().getName());
        viewHolder.exerciseSub.setText("Sets: " + blockExercise.getNumberSets() + " Reps per set: " + blockExercise.getRepsPerSet()[0]);

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBlockExercises.remove(blockExercise);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mBlockExercises.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseTitle;
        TextView exerciseSub;
        ImageButton deleteButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTitle = itemView.findViewById(R.id.add_program_title_tv);
            exerciseSub = itemView.findViewById(R.id.add_program_sub_tv);
            deleteButton = itemView.findViewById(R.id.add_program_delete_btn);
        }
    }
}
