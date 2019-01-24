package com.necohorne.gymapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.RecyclerAdaptors.ProgressRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ProgressGridActivity extends AppCompatActivity {

    public static final String TAG = ProgressGridActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_grid);
        RecyclerView recyclerView = findViewById(R.id.progress_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProgressGridActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.measure)));
        ProgressRecyclerAdapter adapter = new ProgressRecyclerAdapter(ProgressGridActivity.this , list);
        recyclerView.setAdapter(adapter);
        setTitle(R.string.my_progress);
    }
}
