package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.necohorne.gymapp.R;

public class MuscleSetRecyclerAdapter extends RecyclerView.Adapter<MuscleSetRecyclerAdapter.ViewHolder> {

    private static final String TAG = MuscleSetRecyclerAdapter.class.getSimpleName();
    private Context mContext;

    public MuscleSetRecyclerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MuscleSetRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_block_list_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleSetRecyclerAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
