package com.team1.proyecto_is.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ViewEvents(navController: NavController){
    ContentViewEvents()
}

@Composable
fun ContentViewEvents(){
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            Text(text = "Hola mundo :3c\nWassup", fontSize = 40.sp, textAlign = TextAlign.Justify)
        }
        }
    }



// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents(){
    ContentViewEvents()
}

