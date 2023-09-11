


package com.team1.proyecto_is.screen

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.R
import com.team1.proyecto_is.model.*
import com.team1.proyecto_is.service.*
import com.team1.proyecto_is.ui.theme.*
import com.team1.proyecto_is.MainActivity


@Composable
fun ViewEvents(navController: NavController, dataBase: DataBase){
    ContentViewEvents(dataBase)
}

@Composable
fun ContentViewEvents(dataBase: DataBase) {
    Column(
        modifier = Modifier.background(Color(0xFFFCFBF2)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Eventos",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify,
                fontFamily = nunito
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = { /* nos va a mandar a pagina de completados*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_check),
                        contentDescription = "",
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 120.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top,

        ){
        Row() {
            // de parametros deberia de tener algo que nos indique el evento que es para asignarle
            // el color y el texto que esta dentro. a lo mejor un objeto
            EventsList(dataBase)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsList(dataBase: DataBase){

    val eventosService = EventosService(dataBase)
    eventosService.SelectAllEvents()


    var listEvents: List<Eventos> = eventosService.SelectAllEvents()
    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        // le puse mientras 10 pero aqui deberia de tomar los datos del select de la db
        items(listEvents) {
                item ->

            val state = rememberDismissState(
                confirmValueChange = {
                    // si el usuario deslizo de derecha a izquierda (eliminar)
                    if (it == DismissValue.DismissedToStart){
                        //elimina el evento
                    } else if (it == DismissValue.DismissedToEnd){
                        // manda el evento a completados
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
                })

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
            fontFamily = nunito
        )

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

@Composable
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
        //3 Hobbies, 6 Eventos, 7 Break - Amarillo
        3,6,7 -> {
            return evento.getDescripcion()
        }
        //4 Comer - Verde
        4 -> {
            return evento.getComida()
        }
        else -> {
            return ""
        }
    }
}



// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents() {
    val mainActivity = MainActivity()
    ContentViewEvents(mainActivity.InitializeDatabaseConnection(LocalContext.current))
}

