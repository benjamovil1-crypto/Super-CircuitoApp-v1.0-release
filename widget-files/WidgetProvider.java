package com.benjamovil.supercircuitoapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // El bloque try-catch actúa como escudo anti-cierres
        try {
            // 1. Conectar con el puente de datos
            SharedPreferences prefs = context.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);
            
            // 2. Extraer datos
            String comida = prefs.getString("comida_hoy", "Sin alimento registrado");
            String grupoBenjamin = prefs.getString("grupo_benjamin", "Sin registro");
            String grupoBeatriz = prefs.getString("grupo_beatriz", "Sin registro");

            for (int appWidgetId : appWidgetIds) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                
                // 3. Inyectar textos
                views.setTextViewText(R.id.tv_comida, comida);
                views.setTextViewText(R.id.tv_grupo_benjamin, grupoBenjamin);
                views.setTextViewText(R.id.tv_grupo_beatriz, grupoBeatriz);

                // 4. Acción de toque para abrir app
                Intent intent = new Intent(context, MainActivity.class);
                int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flags);
                views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Si hay error interno, lo ignora silenciosamente
        }
    }
}
