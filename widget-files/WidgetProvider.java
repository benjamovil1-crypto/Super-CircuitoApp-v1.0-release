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
        try {
            SharedPreferences prefs = context.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);
            
            // Extraer todos los datos del puente
            String congNombre = prefs.getString("congregacion_nombre", "Super CircuitoApp");
            String comida = prefs.getString("comida_hoy", "🍽️ Comida: Sin asignar");
            String pastoreo = prefs.getString("pastoreo_hoy", "🏠 Pastoreo: Ninguno hoy");
            String actividad = prefs.getString("actividad_hoy", "🔔 Actividad: Ninguna");
            String grupoBenjamin = prefs.getString("grupo_benjamin", "Benjamín: -");
            String grupoBeatriz = prefs.getString("grupo_beatriz", "Beatriz: -");

            for (int appWidgetId : appWidgetIds) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                
                // Inyectar en cada elemento visual
                views.setTextViewText(R.id.tv_congregacion, congNombre);
                views.setTextViewText(R.id.tv_comida, comida);
                views.setTextViewText(R.id.tv_pastoreo, pastoreo);
                views.setTextViewText(R.id.tv_actividad, actividad);
                views.setTextViewText(R.id.tv_grupo_benjamin, grupoBenjamin);
                views.setTextViewText(R.id.tv_grupo_beatriz, grupoBeatriz);

                // Acción de toque para abrir app
                Intent intent = new Intent(context, MainActivity.class);
                int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flags);
                views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
