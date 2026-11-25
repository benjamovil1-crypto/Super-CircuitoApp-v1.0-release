package com.benjamovil.supercircuitoapp;

import android.os.Bundle;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        actualizarWidget();
    }

    @Override
    public void onPause() {
        super.onPause();
        actualizarWidget();
    }

    private void actualizarWidget() {
        try {
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(getApplicationContext());
            Class<?>[] providers = new Class<?>[]{ WidgetProvider.class, CongWidgetProvider.class };

            for (Class<?> cls : providers) {
                ComponentName widgetComponent = new ComponentName(getApplicationContext(), cls);
                int[] widgetIds = widgetManager.getAppWidgetIds(widgetComponent);
                if (widgetIds != null && widgetIds.length > 0) {
                    Intent intent = new Intent(getApplicationContext(), cls);
                    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds);
                    sendBroadcast(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
