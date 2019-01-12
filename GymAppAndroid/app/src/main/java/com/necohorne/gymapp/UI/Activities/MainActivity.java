package com.necohorne.gymapp.UI.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Data.ProgramDatabase;
import com.necohorne.gymapp.Utils.RecyclerAdaptors.MainRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    //UI Elements
    private TextView dayOfWeekTextview;
    private TextView musclesTextview;
    private RecyclerView mRecyclerView;
    public MainRecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;

    //Data Objects
    public String mDay;
    public ProgramDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProgram = new Intent(MainActivity.this, AddProgramActivity.class);
                startActivity(addProgram);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        initUI();
        mDatabase = ProgramDatabase.getInstance(getApplicationContext());
        new DatabaseOperation().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new DatabaseOperation().execute();
    }

    private void initUI(){
        mProgressBar = findViewById(R.id.main_progressbar);
        dayOfWeekTextview = findViewById(R.id.day_ofweek_tv);
        mDay = getDay();
        dayOfWeekTextview.setText(mDay);
        initRecycler();
        musclesTextview = findViewById(R.id.muscle_groups_tv);

    }

    private void initRecycler(){
        mRecyclerView = findViewById(R.id.recycler_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){
            case R.id.nav_camera:
                //
                break;
            case R.id.nav_gallery:
                //
                break;
            case R.id.nav_slideshow:
                //
                break;
            case R.id.nav_manage:
                //
                break;
            case R.id.nav_share:
                //
                break;
            case R.id.nav_send:
                //
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getDay(){
        Date date = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(date);
    }

    public class DatabaseOperation extends AsyncTask<Void, Void, Program>{

        @Override
        protected Program doInBackground(Void... voids) {
            return mDatabase.ProgramDao().searchProgramForDay(mDay);
        }

        @Override
        protected void onPostExecute(Program program) {
            if(program != null){
                mAdapter = new MainRecyclerAdapter(getApplicationContext(), program);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}
