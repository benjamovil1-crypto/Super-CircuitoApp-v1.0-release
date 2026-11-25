package com.benjamovil.supercircuitoapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class CongWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        try {
            SharedPreferences prefs = context.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);

            String titulo = prefs.getString("cong_4x6_titulo", "📍 Congregación");
            String publicadores = prefs.getString("cong_4x6_publicadores", "👥 Publicadores: —");
            String precursores = prefs.getString("cong_4x6_precursores", "🏃 Precursores: —");
            String cursos = prefs.getString("cong_4x6_cursos", "📚 Total de cursos: —");
            String irregulares = prefs.getString("cong_4x6_irregulares", "⚠️ Irregulares: —");
            String rvmc = prefs.getString("cong_4x6_rvmc", "RVMC: —");
            String rp = prefs.getString("cong_4x6_rp", "R.P.: —");
            String horSem = prefs.getString("cong_4x6_hor_sem", "📅 Entresemana: —");
            String horFin = prefs.getString("cong_4x6_hor_fin", "🏛️ Fin de Semana: —");
            String horPrec = prefs.getString("cong_4x6_hor_prec", "📖 Precursores: —");
            String horNomb = prefs.getString("cong_4x6_hor_nomb", "👔 Nombrados: —");

            for (int appWidgetId : appWidgetIds) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_cong_layout);

                views.setTextViewText(R.id.tv_cong_4x6_titulo, titulo);
                views.setTextViewText(R.id.tv_cong_4x6_publicadores, publicadores);
                views.setTextViewText(R.id.tv_cong_4x6_precursores, precursores);
                views.setTextViewText(R.id.tv_cong_4x6_cursos, cursos);
                views.setTextViewText(R.id.tv_cong_4x6_irregulares, irregulares);
                views.setTextViewText(R.id.tv_cong_4x6_rvmc, rvmc);
                views.setTextViewText(R.id.tv_cong_4x6_rp, rp);
                views.setTextViewText(R.id.tv_cong_4x6_hor_sem, horSem);
                views.setTextViewText(R.id.tv_cong_4x6_hor_fin, horFin);
                views.setTextViewText(R.id.tv_cong_4x6_hor_prec, horPrec);
                views.setTextViewText(R.id.tv_cong_4x6_hor_nomb, horNomb);

                Intent intent = new Intent(context, MainActivity.class);
                int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent, flags);
                views.setOnClickPendingIntent(R.id.widget_cong_container, pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
