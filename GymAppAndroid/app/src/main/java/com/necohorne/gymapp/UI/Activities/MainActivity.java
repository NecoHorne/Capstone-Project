package com.necohorne.gymapp.UI.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.necohorne.gymapp.Models.MuscleSet;
import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.UI.Dialog.BasicInfoDialog;
import com.necohorne.gymapp.Utils.Constants;
import com.necohorne.gymapp.Utils.Data.MeasurementsDatabase;
import com.necohorne.gymapp.Utils.Data.ProgramDatabase;
import com.necohorne.gymapp.Utils.RecyclerAdaptors.MainRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView musclesTextview;
    private RecyclerView mRecyclerView;
    public MainRecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;

    //Data Objects
    public String mDay;
    public ProgramDatabase mDatabase;
    private SharedPreferences mSharedPreferences;
    private boolean prefBool;

    private Intent mLogOutIntent;

    //TODO Build Widget

    //------------LIFE CYCLE------------//
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
        mLogOutIntent = new Intent( MainActivity.this, LoginActivity.class );
        checkAuthenticationState();

        mSharedPreferences = getSharedPreferences(Constants.PREFS,0);
        checkPrefs();
        basicInfoDialogPrompt();

        mDatabase = ProgramDatabase.getInstance(getApplicationContext());
        new DatabaseOperation().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new DatabaseOperation().execute();
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
        BasicInfoDialog dialog = new BasicInfoDialog();
        switch(id){
            case R.id.nav_measure:
                if(prefBool){
                    startActivity(new Intent(MainActivity.this, MeasureActivity.class));
                } else {
                    dialog.show(getFragmentManager(), "info_dialog_prompt");
                }
                break;
            case R.id.nav_program:
                startActivity(new Intent(MainActivity.this, MyProgramActivity.class));
                break;
            case R.id.nav_update:
                dialog.show(getFragmentManager(), "info_dialog_prompt");
                break;
            case R.id.nav_stats:
                if(prefBool){
                    new CheckMeasurementDB().execute();
                } else{
                    dialog.show(getFragmentManager(), "info_dialog_prompt");
                }
                break;
            case R.id.nav_progress:
                startActivity(new Intent(MainActivity.this, ProgressGridActivity.class));
                break;
            case R.id.nav_share:
                //
                break;
            case R.id.nav_logout:
                logOut();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //------------METHODS------------//
    private void checkAuthenticationState() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            mLogOutIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( mLogOutIntent );
            finish();
        }
    }

    private void logOut() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText( MainActivity.this, "Successfully logged out", Toast.LENGTH_LONG ).show();
            startActivity( mLogOutIntent );
            finish();
        }
    }

    private void initUI(){
        mProgressBar = findViewById(R.id.main_progressbar);
        //UI Elements
        TextView dayOfWeekTextview = findViewById(R.id.day_ofweek_tv);
        mDay = getDay();
        dayOfWeekTextview.setText(mDay);
        initRecycler();
        musclesTextview = findViewById(R.id.muscle_groups_tv);
        //ad view
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void initRecycler(){
        mRecyclerView = findViewById(R.id.recycler_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void checkPrefs(){
        boolean age = mSharedPreferences.contains( Constants.AGE);
        boolean height = mSharedPreferences.contains( Constants.HEIGHT);
        boolean activity = mSharedPreferences.contains( Constants.ACTIVITY);
        boolean sex = mSharedPreferences.contains( Constants.SEX);
        prefBool = age && height && activity && sex;
    }

    private void basicInfoDialogPrompt() {
        //check if the shared preferences contain the basic info of the user if not prompt the user to add details
        if(!prefBool){
            BasicInfoDialog dialog = new BasicInfoDialog();
            dialog.show(getFragmentManager(), "info_dialog_prompt");
        }
    }

    public String getDay(){
        Date date = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(date);
    }

    //------------ASYNC TASKS------------//
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

                StringBuilder sb = new StringBuilder();
                ArrayList<MuscleSet> muscleSets = program.getSets();
                for(int i = 0; i < muscleSets.size(); i++) {
                    sb.append(muscleSets.get(i).getMuscleName());
                    if(i != muscleSets.size() - 1){
                        sb.append(", ");
                    }
                }
                musclesTextview.setText(sb.toString());
            }
        }
    }

    public class CheckMeasurementDB extends AsyncTask<Void, Void, Integer>{

        @Override
        protected Integer doInBackground(Void... voids) {
            return MeasurementsDatabase.getInstance(getApplicationContext()).MeasurementDao().dbCount();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if(integer > 0){
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "You have not added any measurements yet, Please add a measurement and try again", Toast.LENGTH_LONG).show();
            }
        }
    }
}
