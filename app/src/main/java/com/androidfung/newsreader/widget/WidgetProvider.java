package com.androidfung.newsreader.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.androidfung.newsreader.R;


/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_collection);
        views.setTextViewText(R.id.appwidget_text, widgetText);


        appWidgetManager.updateAppWidget(appWidgetId, views);


//        Intent intent = new Intent(context, MyStocksActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        views.setOnClickPendingIntent(R.id.widget, pendingIntent);


        setRemoteAdapter(context, views);


//        Intent clickIntentTemplate = new Intent(context, MyStocksActivity.class);
//        PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
//                .addNextIntentWithParentStack(clickIntentTemplate)
//                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setPendingIntentTemplate(R.id.widget_list, clickPendingIntentTemplate);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param context the context used to launch the intent
     * @param views   RemoteViews to set the RemoteAdapter
     */
    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, WidgetRemoteViewsService.class));
    }


}

