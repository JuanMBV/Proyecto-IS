package com.team1.proyecto_is

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.telephony.mbms.MbmsErrors.InitializationErrors
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Eventos
import com.team1.proyecto_is.navigation.AppNavigation
import com.team1.proyecto_is.screen.EventsList
import com.team1.proyecto_is.service.EventosService
import com.team1.proyecto_is.service.PlantillaService
import com.team1.proyecto_is.ui.theme.ProyectoISTheme
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
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
                }
            }
        }
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

    EventosService(db).InsertEstudiar("Ingenieria de software", LocalDateTime.of(2023, 9, 21, 18, 30), LocalDateTime.of(2023, 9, 21, 20, 30))
    EventosService(db).InsertEjercicio("Pierna", LocalDateTime.of(2023, 9, 22, 18, 30), LocalDateTime.of(2023, 9, 22, 18, 30))
    EventosService(db).InsertHobbie("Jugar", LocalDateTime.of(2023, 9, 23, 18, 30), "Saltillo")
    EventosService(db).InsertComer("Papas", LocalDateTime.of(2023, 9, 24, 18, 30), LocalDateTime.of(2023, 9, 24, 18, 30))
    EventosService(db).InsertTarea("Tarea redes", "Enrutamiento", LocalDateTime.of(2023, 9, 25, 18, 30))
    EventosService(db).InsertBreak("Descansito",LocalDateTime.of(2023, 9, 26, 18, 30), LocalDateTime.of(2023, 9, 26, 18, 31))
    EventosService(db).InsertEventos("Baby Shower", "Salon de baby shower", LocalDateTime.of(2023, 9, 27, 18, 30))
    EventosService(db).InsertExamen("Automatas", LocalDateTime.of(2023, 9, 28, 18, 30))
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