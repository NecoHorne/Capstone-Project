package com.necohorne.gymapp.UI.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.necohorne.gymapp.Models.MuscleSet;
import com.necohorne.gymapp.Models.Program;
import com.necohorne.gymapp.R;
import com.necohorne.gymapp.Utils.Data.ProgramDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WidgetIntentService extends IntentService {

    public static final String ACTION_UPDATE_WIDGET = "com.necohorne.gymapp.UI..Widget.action.update_widget";

    public WidgetIntentService() {
        super("WidgetIntentService");
    }

    public static void startActionUpdateWidget(Context context){
        Intent intent = new Intent(context, WidgetIntentService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_WIDGET.equals(action)) {
                handleActionUpdateWidget();
            }
        }
    }

    private void handleActionUpdateWidget() {

        ProgramDatabase mDatabase = ProgramDatabase.getInstance(getApplicationContext());
        String mDay = getDay();
        Program program = mDatabase.ProgramDao().searchProgramForDay(mDay);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, ProgramWidget.class));

        if(program != null){
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_program_tv);

            StringBuilder sb = new StringBuilder();
            ArrayList<MuscleSet> muscleSets = program.getSets();
            for(int i = 0; i < muscleSets.size(); i++) {
                sb.append(muscleSets.get(i).getMuscleName());
                if(i != muscleSets.size() - 1){
                    sb.append(", ");
                }
            }
            String muscles = sb.toString();
            ProgramWidget.updateAppWidget(this, appWidgetManager, appWidgetIds, mDay, muscles);
        } else {
            ProgramWidget.updateAppWidget(this, appWidgetManager, appWidgetIds, mDay, getString(R.string.widget_no_program));
        }

    }

    public String getDay(){
        Date date = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(date);
    }
}
