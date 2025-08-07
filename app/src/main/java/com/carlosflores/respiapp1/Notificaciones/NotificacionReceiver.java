package com.carlosflores.respiapp1.Notificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.carlosflores.respiapp1.BD.DBManager;
import com.carlosflores.respiapp1.R;

public class NotificacionReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {
        DBManager dbManager = new DBManager( context);
      // Enviar la notificación
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String canalId = "canal_sabado";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(canalId, "Notificaciones de sábado", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(canal);
        }

        String mensaje;
        try {
            mensaje = "Respiapp te espera, " + dbManager.valores();
        } catch (Exception e) {
            mensaje = "Respiapp te espera. ¡Realiza tus ejercicios!";
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, canalId)
                .setSmallIcon(R.drawable.img)
                .setContentTitle("¡Ya es sábado!")
                .setContentText(mensaje )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        manager.notify(1, builder.build());

        // ⚠️ Volver a programar la notificación para el próximo sábado
        NotificacionUtils.programarProximoSabado(context);
    }


}

