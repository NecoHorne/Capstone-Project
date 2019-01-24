package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.necohorne.gymapp.Models.BlockExercise;
import com.necohorne.gymapp.R;

public class ExerciseBlockRecyclerAdapter extends RecyclerView.Adapter<ExerciseBlockRecyclerAdapter.ViewHolder> {

    private static final String TAG = ExerciseBlockRecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private BlockExercise mBlockExercise;

    ExerciseBlockRecyclerAdapter(Context context, BlockExercise blockExercises) {
        mContext = context;
        mBlockExercise = blockExercises;
    }

    @NonNull
    @Override
    public ExerciseBlockRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_rep_list_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseBlockRecyclerAdapter.ViewHolder viewHolder, int position) {
        String reps = mBlockExercise.getRepsPerSet()[position];

        String set = mContext.getString(R.string.recycler_set) + String.valueOf(position + 1);
        String rep = mContext.getString(R.string.recycler_rep) + reps;

        viewHolder.numberOfSets.setText(set);
        viewHolder.numberOfReps.setText(rep);
    }

    @Override
    public int getItemCount() {
        return mBlockExercise.getNumberSets();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberOfSets;
        TextView numberOfReps;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberOfSets = itemView.findViewById(R.id.set_number_tv);
            numberOfReps = itemView.findViewById(R.id.rep_num_tv);
        }
    }
}
