package com.team1.proyecto_is.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.team1.proyecto_is.R
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.ui.theme.nunito

@Composable
fun SelectTemplate(navController: NavController){
    ContentSelectTemplate(navController)
}

@Composable
fun ContentSelectTemplate(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(0xFFFCFBF2))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                navController.navigate(AppScreens.ViewEvents.route)
            }) {
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
            Text(
                text = "Selecciona tu\nplantilla",
                modifier = Modifier.padding(bottom = 20.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = nunito,
                color = Color(0xFF222222))
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
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 180.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = size.width / 2

        val colors = listOf(
            Color(0xFF97C437), Color(0xFFFACE48), Color(0xFFF28E3C), Color(0xFFE63632),
            Color(0xFFED4D83), Color(0xFFBE68C9), Color(0xFF3D65CA), Color(0xFF52C0C1)
        )

        val startAngle = -45f

        for (i in 0 until 8) {
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

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectTemplate(){
    val navController = rememberNavController()
    ContentSelectTemplate(navController)
}