package com.team1.proyecto_is

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.navigation.AppNavigation
import com.team1.proyecto_is.notification.AlarmNotification
import com.team1.proyecto_is.notification.AlarmNotification.Companion.NOTIFICATION_ID
import com.team1.proyecto_is.service.EventosService
import com.team1.proyecto_is.service.PlantillaService
import com.team1.proyecto_is.ui.theme.ProyectoISTheme
import java.time.LocalDateTime
import java.util.Calendar

class MainActivity : ComponentActivity() {
    companion object{
        const val CHANNEL_ID = "channelId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoISTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize Data Base
                    val db = InitializeDatabaseConnection(this)

                    InsertPlantillas(db)
                    PrintPlantillas(db)
                    InsertEventos(db)
                    PrintEventos(db)
                    AppNavigation(db)

                    // Notifications
                    createNotificationChannel()
                    sheduleNotification()
                }
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun sheduleNotification() {
        val intent = Intent(applicationContext, AlarmNotification::class.java)

        val nearestEvent = EventosService(DataBase(applicationContext)).getNearestEvent()

        nearestEvent?.forEach{ event ->
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = event.getFechaInicial()?.atZone(java.time.ZoneId.systemDefault())?.toInstant()!!.toEpochMilli()?:0

            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }


    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Notificacion de eventos",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = this.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

fun InitializeDatabaseConnection(context: Context): DataBase{
    val db = DataBase(context)
    return db
}

fun InsertPlantillas(db: DataBase){
    val plantillas = listOf("Estudiar", "Ejercicio", "Hobbies", "Comer", "Tarea", "Break", "Eventos", "Examen")
    val plantillaService = PlantillaService(db)

    for (nombrePlantilla in plantillas) {
        if (!plantillaService.ExistePlantilla(nombrePlantilla)) {
            plantillaService.InsertPlantilla(nombrePlantilla)
        }
    }
}

fun InsertEventos(db: DataBase) {
    EventosService(db).InsertEstudiar("Ingenieria de software", LocalDateTime.of(2023, 12, 3, 12, 58), LocalDateTime.of(2023, 9, 21, 20, 30))
    EventosService(db).InsertEstudiar("Redes", LocalDateTime.of(2023, 12, 3, 12, 59), LocalDateTime.of(2023, 9, 21, 20, 30))
    EventosService(db).InsertEstudiar("Si", LocalDateTime.of(2023, 12, 3, 12, 0), LocalDateTime.of(2023, 9, 21, 20, 30))
}

fun PrintPlantillas(db: DataBase){
    var lista = PlantillaService(db).SelectNamePlantilla()


    lista.forEach{elemento ->
        println(elemento)
    }
}

fun PrintEventos(db: DataBase){
    var lista = EventosService(db).SelectAllEvents()

    lista.forEach{element ->
        println(element.toString())
    }
}
/*
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    ProyectoISTheme {
        val db = InitializeDatabaseConnection(this)
        AppNavigation()
    }

}
*/