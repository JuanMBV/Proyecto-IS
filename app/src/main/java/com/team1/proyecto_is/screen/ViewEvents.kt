package com.team1.proyecto_is.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.team1.proyecto_is.R
import com.team1.proyecto_is.ui.theme.*


@Composable
fun ViewEvents(navController: NavController){
    ContentViewEvents()
}

@Composable
fun ContentViewEvents() {
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
                textAlign = TextAlign.Justify
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
            EventsList()
            }
        }
    }



@Composable
fun EventsList(){
    val datos : List<String> = listOf(
        "Evento 1",
        "Evento 2",
        "Evento 3",
        "Evento 4",
        "Evento 5")
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        // le puse mientras 10 pero aqui deberia de tomar los datos del select de la db
        items(datos) {
            item -> ListItemRow(item,)
        }
    }
}


@Composable
// parametros
//descripcion
//num plantilla
fun ListItemRow(item: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            // el color se va a definir de acuerdo a la plantilla
            .background(ChooseColor(3))
            .padding(horizontal = 20.dp, vertical = 15.dp)

    ){
        Text(item,
            fontSize = 20.sp,
            )
    }
}

// metodo en el que dependiendo del número de plantilla, asigna color
//al fondo del objeto de la lista
@Composable
fun ChooseColor(idPlantilla : Int) : Color {
    when (idPlantilla) {
        //1 Estudiar - Rojo
        1 -> {
            return Color(0xFFE63632)
        }
        //2 Ejercicio - Naranja
        2 -> {
            return Color(0xFFF28E3C)
        }
        //3 Hobbies - Amarillo
        3 -> {
            return Color(0xFFFACE48)
        }
        //4 Comer - Verde
        4 -> {
            return Color(0xFF97C437)
        }
        //5 Tarea - Turquesa
        5 -> {
            return Color(0xFF52C0C1)
        }
        //6 Break - Azul
        6 -> {
            return Color(0xFF3D65CA)
        }
        //7 Eventos - Morado
        7 -> {
            return Color(0xFFBE68C9)
        }
        //8 Examen - Rosa
        8 -> {
            return Color(0xFFED4D83)
        }
        else -> {
            return Color(0xFFFCFBF2)
        }
    }
}



// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents() {
    ContentViewEvents()
}


