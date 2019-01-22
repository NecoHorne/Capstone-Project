package com.necohorne.gymapp.UI.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.RecyclerAdaptors.ProgressRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgressGridActivity extends AppCompatActivity {

    public static final String TAG = ProgressGridActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_grid);
        mRecyclerView = findViewById(R.id.progress_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProgressGridActivity.this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.measure)));
        ProgressRecyclerAdapter adapter = new ProgressRecyclerAdapter(getApplicationContext(),mList);
        mRecyclerView.setAdapter(adapter);
        setTitle(R.string.my_progress);
    }
}
