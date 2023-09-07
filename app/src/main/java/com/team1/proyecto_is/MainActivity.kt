package com.team1.proyecto_is

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.telephony.mbms.MbmsErrors.InitializationErrors
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.navigation.AppNavigation
import com.team1.proyecto_is.service.PlantillaService
import com.team1.proyecto_is.ui.theme.ProyectoISTheme

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

                    AppNavigation()
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

fun PrintPlantillas(db: DataBase){
    var lista = PlantillaService(db).SelectNamePlantilla()

    lista.forEach{elemento ->
        println(elemento)
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    ProyectoISTheme {
        AppNavigation()
    }

}