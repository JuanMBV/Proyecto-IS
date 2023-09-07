package com.team1.proyecto_is.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
        modifier = Modifier.padding(top = 25.dp, end = 15.dp)
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
        modifier = Modifier.padding(start = 30.dp, top = 120.dp),
        verticalArrangement = Arrangement.Top,

    ){
        Row() {
            LazyColumn {
                items(10) {
                    Text(
                        text = "hola",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(7.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}



// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents() {
    ContentViewEvents()
}


