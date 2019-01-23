package com.necohorne.gymapp.UI.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.necohorne.gymapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class ProgramWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId, String title, String muscles) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.program_widget);
        views.setTextViewText(R.id.appwidget_text, title);
        views.setTextViewText(R.id.widget_program_tv, muscles);

        //construct the pending intent
        Intent updateIntent = new Intent(context, WidgetIntentService.class);
        updateIntent.setAction(WidgetIntentService.ACTION_UPDATE_WIDGET);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, updateIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.widget_program_tv, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        WidgetIntentService.startActionUpdateWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

