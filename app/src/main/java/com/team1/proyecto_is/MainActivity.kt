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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
                    // Notifications
                    createNotificationChannel()
                    //InsertEventos(db)
                    PrintEventos(db)
                    AppNavigation(db)



                    //sheduleNotification()
                }
            }
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

    /**
    fun InsertEventos(db: DataBase) {
        EventosService(db).InsertEvento(1, "Matematicas", null, null, null, null, 0, LocalDateTime.now(), LocalDateTime.of(2023, 9, 7, 18, 30), LocalDateTime.of(2023, 9, 7, 19, 0), "")
        EventosService(db).InsertEvento(2, null, "Pierna", null, null, null, 0, LocalDateTime.now(), LocalDateTime.of(2023, 9, 7, 18, 30), LocalDateTime.of(2023, 9, 7, 19, 0), "")
        EventosService(db).InsertEvento(8, "Redes", null, null, null, null,0, LocalDateTime.now(), LocalDateTime.of(2023, 9, 7, 18, 30), null, "")
    }
*/
    fun PrintPlantillas(db: DataBase){
        var lista = PlantillaService(db).SelectNamePlantilla()

        lista.forEach{elemento ->
            println(elemento)
        }
    }

    fun PrintEventos(db: DataBase) {
        var lista = EventosService(db).SelectAllEvents()

        lista.forEach { element ->
            println(element.toString())
        }
    }

/**
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    ProyectoISTheme {
        AppNavigation(MainActivity().InitializeDatabaseConnection(LocalContext.current))
    }

}
        **/