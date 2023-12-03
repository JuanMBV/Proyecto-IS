package com.team1.proyecto_is.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Eventos
import com.team1.proyecto_is.service.EventosService
import com.team1.proyecto_is.ui.theme.*


// declaración de variables
var plantillaGlobal = ""
var idPlantillaGlobal = sendIdPlantilla(plantillaGlobal)



// esta clase es la que guarda las variables y metodos globales

//@Composable
fun ChooseText(evento: Eventos) : String? {
    when (evento.getPlantilla()?.getIdPlantilla()) {
        //1 Estudiar, 5 Tareas, 8 Examen - Materia
        1, 5, 8 -> {
            return evento.getMateria()
        }
        //2 Ejercicio - Naranja
        2 -> {
            return evento.getParteCuerpo()
        }
        //3 Hobbies, 6 Eventos
        3,6 -> {
            return evento.getDescripcion()
        }
        //4 Comer - Verde
        4 -> {
            return evento.getComida()
        }
        // 7 Break - Amarillo
        7 -> {
            return "Break"
        }
        else -> {
            return ""
        }
    }
}

// metodo en el que dependiendo del número de plantilla, asigna color
//al fondo del objeto de la lista
@Composable
fun ChooseColor(idPlantilla : Int?) : Color {
    when (idPlantilla) {
        //1 Estudiar - Rojo
        1 -> {
            return rojo
        }
        //2 Ejercicio - Naranja
        2 -> {
            return naranja
        }
        //3 Hobbies - Amarillo
        3 -> {
            return amarillo
        }
        //4 Comer - Verde
        4 -> {
            return verde
        }
        //5 Tarea - Turquesa
        5 -> {
            return turquesa
        }
        //6 Break - Azul
        6 -> {
            return azul
        }
        //7 Eventos - Morado
        7 -> {
            return morado
        }
        //8 Examen - Rosa
        8 -> {
            return rosa
        }
        else -> {
            return fondo
        }
    }
}


// metodo en el que dependiendo del número de plantilla, asigna color
//al fondo del objeto de la lista. VERSION COMPLETADOS
@Composable
fun ChooseColorCompletado(idPlantilla : Int?) : Color {
    when (idPlantilla) {
        //1 Estudiar - Rojo
        1 -> {
            return rojoC
        }
        //2 Ejercicio - Naranja
        2 -> {
            return naranjaC
        }
        //3 Hobbies - Amarillo
        3 -> {
            return amarilloC
        }
        //4 Comer - Verde
        4 -> {
            return verdeC
        }
        //5 Tarea - Turquesa
        5 -> {
            return turquesaC
        }
        //6 Break - Azul
        6 -> {
            return azulC
        }
        //7 Eventos - Morado
        7 -> {
            return moradoC
        }
        //8 Examen - Rosa
        8 -> {
            return rosaC
        }
        else -> {

            return fondo
        }
    }
}

/**
 * Metodo que envia el ID de plantilla de acuerdo al nombre porque estan harcoded
 */
fun sendIdPlantilla(plantilla: String) : Int {
    Log.d("Plantilla ID", "Se está asignando ID de plantilla a la variable global")
    when (plantilla) {
        "Estudiar" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 1
        }
        "Ejercicio" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 2
        }
        "Hobbies" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 3
        }
        "Comer" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 4
        }
        "Tarea" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 5
        }
        "Break" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 6
        }
        "Eventos" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 7
        }
        "Examen" -> {
            Log.d("Plantilla ID", "Se asigno correctamente")
            return 8
        }
        else -> {
            Log.d("Error en ID", "Se asigno ID como 0")
            return 0;
        }
    }
}

@Composable
// parametros
//descripcion
//num plantilla
fun ListItemRow(evento : Eventos){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            // el color se va a definir de acuerdo a la plantilla
            .background(
                ChooseColor(
                    evento
                        .getPlantilla()
                        ?.getIdPlantilla()
                )
            )
            .padding(horizontal = 20.dp, vertical = 15.dp)
        //.clickable(onClick = (TODO()))

    ){
        Text(
            text = ChooseText(evento).toString(),
            fontSize = 20.sp,
            fontFamily = nunito,
            color = Color.White
        )

    }
}

@Composable
fun ListItemRowCompletado(evento : Eventos){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            // el color se va a definir de acuerdo a la plantilla
            .background(
                ChooseColorCompletado(
                    evento
                        .getPlantilla()
                        ?.getIdPlantilla()
                )
            )
            .padding(horizontal = 20.dp, vertical = 15.dp)
        //.clickable(onClick = (TODO()))

    ){
        Text(
            text = ChooseText(evento).toString(),
            fontSize = 20.sp,
            fontFamily = nunito,
            color = Color.White
        )
    }
}


// metodo que muestra en pantalla los eventos completados
// por eventos completados, nos referimos a TODAS las plantillas
// como ya estan completados, no permite el volver a completar
// solo el eliminado
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsListCompleted(dataBase: DataBase){
    //val popup = remember { mutableStateOf(false) }
    val eventosService = EventosService(dataBase)
// Cambia la declaración de listEvents
    val listEvents = remember { mutableStateListOf<Eventos>() }
    listEvents.clear() // Limpia la lista antes de agregar nuevos eventos
// Llena la lista con los elementos de eventosService.SelectAllEvents()
    Log.d("Plantilla ID", "Se hara la query de selectPerPlantilla")
    listEvents.addAll(eventosService.SelectCompleteEvents())
    Log.d("Plantilla ID", "Se hizo la query de selectPerPlantilla")

    val contexto = LocalContext.current
    print(listEvents)

    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        // por cada item de la lista de objetos listEvents
        items(listEvents, key = { listEvents -> listEvents.hashCode()}) {
                item ->
            Log.d("Plantilla ID", "primer item de la lista")
            val state = rememberDismissState(
                confirmValueChange = {
                    when(it){
                        DismissValue.DismissedToEnd ->{
                            Log.d("Plantilla ID", "ENTRO AL WHEN")
                            // para completar el evento (izquierda a derecha)
                            eventosService.CompleteEvent(item.getIdEventos())
                            Log.d("Plantilla ID", "OBTUVO EL ID DEL EVENTO")
                            listEvents.remove(item)
                            Log.d("Plantilla ID", "REMOVIO EL EVENTO DE LA PANTALLA")
                            Toast.makeText(contexto, "Completado", Toast.LENGTH_SHORT).show()
                            //}
                        }
                        DismissValue.DismissedToStart ->{
                            // NADA
                        }
                        DismissValue.Default->{
                            // NADA
                        }
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color =
                        when(state.dismissDirection){
                            DismissDirection.EndToStart -> Color.Transparent
                            DismissDirection.StartToEnd -> Color.Transparent
                            null -> Color.Transparent
                        }
                },
                dismissContent = {
                    ListItemRow(item)
                },
                directions = setOf(DismissDirection.StartToEnd),
            )

        }
    }
}

// metodo que nos muestra en pantalla los eventos por plantilla
// solo los eventos por plantilla que faltan de completar
// como no estan completados, se pueden marcar como completados
// o se pueden eliminar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsListPlantilla(dataBase: DataBase){
    //val popup = remember { mutableStateOf(false) }
    val eventosService = EventosService(dataBase)
// Cambia la declaración de listEvents
    val listEvents = remember { mutableStateListOf<Eventos>() }
    listEvents.clear() // Limpia la lista antes de agregar nuevos eventos
// Llena la lista con los elementos de eventosService.SelectAllEvents()
    Log.d("Plantilla ID", "Se hara la query de selectPerPlantilla")
    listEvents.addAll(eventosService.SelectPerPlantilla(sendIdPlantilla(plantillaGlobal)))
    Log.d("Plantilla ID", "Se hizo la query de selectPerPlantilla")

    val contexto = LocalContext.current
    print(listEvents)

    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        // por cada item de la lista de objetos listEvents
        items(listEvents, key = { listEvents -> listEvents.hashCode()}) {
                item ->
            Log.d("Plantilla ID", "primer item de la lista")
            val state = rememberDismissState(
                confirmValueChange = {
                    when(it){
                        DismissValue.DismissedToEnd ->{
                            // para completar el evento (izquierda a derecha)
                            eventosService.CompleteEvent(item.getIdEventos())
                            listEvents.remove(item)
                            Toast.makeText(contexto, "Completado", Toast.LENGTH_SHORT).show()
                            //}
                        }
                        DismissValue.DismissedToStart ->{
                            //para eliminar el evento (derecha a izquierda)
                            eventosService.DeleteEvent(item.getIdEventos())
                            listEvents.remove(item)
                            Toast.makeText(contexto, "Eliminado", Toast.LENGTH_SHORT).show()
                        }
                        DismissValue.Default->{
                            // cuando lo deja a medias
                        }
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color =
                        when(state.dismissDirection){
                            DismissDirection.EndToStart -> Color.Transparent
                            DismissDirection.StartToEnd -> Color.Transparent
                            null -> Color.Transparent
                        }
                },
                dismissContent = {
                    ListItemRow(item)
                },
            )

        }
    }
}