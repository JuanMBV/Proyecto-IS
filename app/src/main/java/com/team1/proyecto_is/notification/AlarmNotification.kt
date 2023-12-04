package com.team1.proyecto_is.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.MainActivity
import com.team1.proyecto_is.model.Eventos
import com.team1.proyecto_is.service.EventosService
import java.time.LocalDateTime
import java.util.Calendar

class AlarmNotification: BroadcastReceiver(){

    companion object{
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, p1: Intent?) {
        val intent = p1
        val valorRecibido = intent?.getStringExtra("text")
        sendNotification(context, valorRecibido.toString())
    }

    fun sendNotification(context: Context, text: String){
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val notification = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setContentTitle("Tienes un evento pa'")
            .setContentText("Tocó $text")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(text.hashCode(), notification)

    }

}