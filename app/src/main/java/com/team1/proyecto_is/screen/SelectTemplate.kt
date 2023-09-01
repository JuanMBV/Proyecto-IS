package com.team1.proyecto_is.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.team1.proyecto_is.R

@Composable
fun SelectTemplate(navController: NavController){
    ContentSelectTemplate()
}

@Composable
fun ContentSelectTemplate(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.icon_close),
                    contentDescription = ""
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Selecciona tu\nplantilla", fontSize = 30.sp, textAlign = TextAlign.Center)
        }

        Row(
            modifier = Modifier.size(300.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Aqui iria el pastel
            Cake()
        }
    }
}

@Composable
fun Cake(){
    Canvas(
        modifier = Modifier.fillMaxSize().padding(top = 180.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = size.width / 2

        val colors = listOf(
            Color.Red, Color.Blue, Color.Green, Color.Yellow,
            Color.Cyan, Color.Magenta, Color.Gray, Color.Black
        )

        val startAngle = -45f

        for (i in 0 until 8) {
            Box(){
                drawArc(
                    color = colors[i],
                    startAngle = startAngle + i * 45f,
                    sweepAngle = 45f,
                    useCenter = true,
                    topLeft = Offset(centerX - radius, centerY - radius),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
                )
            }

        }
    }
}

@Composable
fun PieChartWithClickableSlices() {
    val colors = listOf(
        Color.Red, Color.Blue, Color.Green, Color.Yellow,
        Color.Cyan, Color.Magenta, Color.Gray, Color.Black
    )
    val startAngle = -45f

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val with =
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = size.width / 3

        for (i in 0 until 8) {
            val sliceAngle = 45f
            val sliceStartAngle = startAngle + i * sliceAngle

            val sliceModifier = Modifier.clickable {
                // Handle click action here
                // For example, you can display a toast with the slice index
                // or navigate to a different screen
                println("Slice $i clicked")
            }

            Canvas(
                modifier = sliceModifier
                    .size(radius * 2)
                    .offset { IntOffset((radius * 2) * i, 0) }
            ) {
                drawArc(
                    color = colors[i],
                    startAngle = sliceStartAngle,
                    sweepAngle = sliceAngle,
                    useCenter = true,
                    topLeft = Offset(centerX - radius, centerY - radius),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    style = Stroke(50f)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectTemplate(){
    ContentSelectTemplate()
}