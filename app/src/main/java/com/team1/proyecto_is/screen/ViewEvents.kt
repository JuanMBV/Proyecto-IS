package com.team1.proyecto_is.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.team1.proyecto_is.R


@Composable
fun ViewEvents(navController: NavController){
    ContentViewEvents()
}

@Composable
fun ContentViewEvents() {
    Column(
        modifier = Modifier
            .padding(top = 25.dp, end = 15.dp)
            .fillMaxWidth()
            .background(Color(0xFFFCFBF2)),
        verticalArrangement = Arrangement.Top,
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                    horizontalArrangement = Arrangement . End,

            ) {
            IconButton(onClick = { /* nos va a mandar a pagina de completados*/ }) {
                Icon(
                    painter = painterResource(R.drawable.icon_check),
                    contentDescription = ""
                )
            }
        }
    }
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
                textAlign = TextAlign.Justify
            )
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
        "Evento 5",)
    LazyColumn(){
        // le puse mientras 10 pero aqui deberia de tomar los datos del select de la db
        items(datos) {
            item -> ListItemRow(item)
        }
    }
}

@Composable
fun ListItemRow(item: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.large)
            .background(Color(0xFFBE68C9))
            .padding(horizontal = 16.dp, vertical = 10.dp)

    ){
        Text(item)
    }
}


// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents() {
    ContentViewEvents()
}


