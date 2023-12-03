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
        sendNotification(context)
    }

    fun sendNotification(context: Context){
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val nearestEvent = EventosService(DataBase(context)).getNearestEvent()

        nearestEvent?.forEach { evento ->
            val text = getTextFromEvent(evento)

            val notification = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setContentTitle("Tienes un evento pa'")
                .setContentText("Toco estudiar pa' $text")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .build()

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(NOTIFICATION_ID, notification)
        }
    }

    fun getTextFromEvent(evento: Eventos): String{
        return when(evento.getIdEvento()){
            1, 5, 8 -> evento.getMateria().toString()
            2 -> evento.getParteCuerpo().toString()
            3, 7 -> evento.getDescripcion().toString()
            4 -> evento.getComida().toString()
            6 -> "Toco descansar pa'"
            else -> "si"
        }
    }
}