package com.benjamovil.supercircuitoapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        
        // 1. Conectar con el almacenamiento local que usa Capacitor
        val prefs = context.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE)
        
        // 2. Extraer los datos (si no hay nada, muestra el texto por defecto)
        val comida = prefs.getString("comida_hoy", "No hay comida programada aún")
        val grupoBenjamin = prefs.getString("grupo_benjamin", "Sin anotar")
        val grupoBeatriz = prefs.getString("grupo_beatriz", "Sin anotar")

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            // 3. Inyectar los textos en el diseño
            views.setTextViewText(R.id.tv_comida, comida)
            views.setTextViewText(R.id.tv_grupo_benjamin, grupoBenjamin)
            views.setTextViewText(R.id.tv_grupo_beatriz, grupoBeatriz)

            // 4. Configurar el toque para abrir la app
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_container, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
