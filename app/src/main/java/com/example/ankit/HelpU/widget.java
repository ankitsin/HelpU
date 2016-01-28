package com.example.ankit.HelpU;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;


public class widget extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        for (int i=0; i<appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];
//            MainActivity.onclickhelp();
            Intent intent = new Intent(context, sendmsg.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
            views.setOnClickPendingIntent(R.id.buttonwidget, pendingIntent);
//
//            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
//    private Runnable mMyRunnable = new Runnable()
//    {
//        @Override
//        public void run()
//        {
//            MainActivity.sendSMSMessage();        }
//    };
}
