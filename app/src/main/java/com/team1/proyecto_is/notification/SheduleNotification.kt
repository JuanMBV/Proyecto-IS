package com.team1.proyecto_is.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDateTime
import java.util.Calendar

class SheduleNotification{
    fun sheduleNotification(context: Context, text: String, formatInicio: LocalDateTime) {
        val intent = Intent(context, AlarmNotification::class.java)
        intent.putExtra("text", text)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = formatInicio.atZone(java.time.ZoneId.systemDefault())?.toInstant()!!.toEpochMilli()?:0

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            text.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

    }
}
