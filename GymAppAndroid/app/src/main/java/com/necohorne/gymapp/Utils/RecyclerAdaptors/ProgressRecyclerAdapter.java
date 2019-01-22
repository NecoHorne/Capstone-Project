package com.necohorne.gymapp.Utils.RecyclerAdaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.necohorne.gymapp.R;
import com.necohorne.gymapp.UI.Activities.ProgressActivity;
import com.necohorne.gymapp.Utils.Constants;

import java.util.ArrayList;

public class ProgressRecyclerAdapter extends RecyclerView.Adapter<ProgressRecyclerAdapter.ViewHolder> {

    private static final String TAG = ProgressRecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<String> mList;

    public ProgressRecyclerAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ProgressRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progress_grid_item, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressRecyclerAdapter.ViewHolder viewHolder, int i) {
        final String position = mList.get(i);

        viewHolder.title.setText(position);
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProgressActivity.class);
                intent.putExtra(Constants.INTENT_PROGRESS, position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView title;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.progress_image_view);
            title = itemView.findViewById(R.id.progress_title_tv);
            mCardView = itemView.findViewById(R.id.progress_cardview);
        }
    }
}
