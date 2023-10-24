package com.team1.proyecto_is.screen

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.team1.proyecto_is.R
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.ui.theme.amarillo
import com.team1.proyecto_is.ui.theme.azul
import com.team1.proyecto_is.ui.theme.fondo
import com.team1.proyecto_is.ui.theme.morado
import com.team1.proyecto_is.ui.theme.naranja
import com.team1.proyecto_is.ui.theme.nunito
import com.team1.proyecto_is.ui.theme.rojo
import com.team1.proyecto_is.ui.theme.rosa
import com.team1.proyecto_is.ui.theme.turquesa
import com.team1.proyecto_is.ui.theme.verde
import kotlin.math.PI
import kotlin.math.atan2


@Composable
fun SelectTemplate(navController: NavController){
    ContentSelectTemplate(navController)
}

@Composable
fun ContentSelectTemplate(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                "Selecciona tu\nplantilla",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = nunito,
                lineHeight = 35.sp,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PieChart(
                modifier = Modifier
                    .size(500.dp),
                input = listOf(
                    // 5 Tarea turquesa
                    PieChartInput(
                        color = turquesa,
                        value = 45,
                        description = "Tarea"
                    ),
                    // 6 Break azul
                    PieChartInput(
                        color = azul,
                        value = 45,
                        description = "Break"
                    ),
                    // 7 Eventos morado
                    PieChartInput(
                        color = morado,
                        value = 45,
                        description = "Eventos"
                    ),
                    // 8 Examen rosa
                    PieChartInput(
                        color = rosa,
                        value = 45,
                        description = "Examen"
                    ),
                    // 1 Estudiar rojo
                    PieChartInput(
                        color = rojo,
                        value = 45,
                        description = "Estudiar"
                    ),
                    // 2 Ejercicio naranja
                    PieChartInput(
                        color = naranja,
                        value = 45,
                        description = "Ejercicio"
                    ),
                    // 3 Hobbies amarillo
                    PieChartInput(
                        color = amarillo,
                        value = 45,
                        description = "Hobbies"
                    ),
                    // 4 Comer verde
                    PieChartInput(
                        color = verde,
                        value = 45,
                        description = "Comer"
                    ),
                ),
                navController = navController
                )
        }
    }
}


@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    radius:Float = 380f,
    innerRadius:Float = 100f,
    input:List<PieChartInput>,
    navController: NavController
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var inputList by remember {
        mutableStateOf(input)
    }
    var isCenterTapped by remember {
        mutableStateOf(false)
    }
    var showPopUp by remember {
        mutableStateOf(false)
    }
    var plantilla : String = ""

    // aqui se hace el if que dependiendo del valor del showPopUp, muestra el popUp


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val tapAngleInDegrees = (-atan2(
                                x = circleCenter.y - offset.y,
                                y = circleCenter.x - offset.x
                            ) * (180f / PI).toFloat() - 90f).mod(360f)
                            val centerClicked = if (tapAngleInDegrees < 90) {
                                offset.x < circleCenter.x + innerRadius && offset.y < circleCenter.y + innerRadius
                            } else if (tapAngleInDegrees < 180) {
                                offset.x > circleCenter.x - innerRadius && offset.y < circleCenter.y + innerRadius
                            } else if (tapAngleInDegrees < 270) {
                                offset.x > circleCenter.x - innerRadius && offset.y > circleCenter.y - innerRadius
                            } else {
                                offset.x < circleCenter.x + innerRadius && offset.y > circleCenter.y - innerRadius
                            }

                            if (centerClicked) {
                                inputList = inputList.map {
                                    it.copy(isTapped = !isCenterTapped)
                                }
                                isCenterTapped = !isCenterTapped
                            } else {
                                val anglePerValue = 360f / input.sumOf {
                                    it.value
                                }
                                var currAngle = 0f
                                inputList.forEach { pieChartInput ->

                                    currAngle += pieChartInput.value * anglePerValue
                                    if (tapAngleInDegrees < currAngle) {
                                        val description = pieChartInput.description
                                        inputList = inputList.map {
                                            if (description == it.description) {
                                                it.copy(isTapped = !it.isTapped)
                                            } else {
                                                it.copy(isTapped = false)
                                            }
                                        }
                                        return@detectTapGestures
                                    }
                                }
                            }
                        }
                    )
                }
        ){
            val width = size.width
            val height = size.height
            circleCenter = Offset(x= width/2f,y= height/2f)

            val totalValue = input.sumOf {
                it.value
            }
            val anglePerValue = 360f/totalValue
            var currentStartAngle = 0f

            inputList.forEach { pieChartInput ->
                val scale = if(pieChartInput.isTapped) 1.1f else 1.0f
                val angleToDraw = pieChartInput.value * anglePerValue
                scale(scale){
                    drawArc(
                        color = pieChartInput.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size = Size(
                            width = radius*2f,
                            height = radius*2f
                        ),
                        topLeft = Offset(
                            (width-radius*2f)/2f,
                            (height - radius*2f)/2f
                        )
                    )
                    currentStartAngle += angleToDraw
                }
                var rotateAngle = currentStartAngle-angleToDraw/2f-90f
                var factor = 1f
                if(rotateAngle>90f){
                    rotateAngle = (rotateAngle+180).mod(360f)
                    factor = -0.92f
                }

                if(pieChartInput.isTapped){
                    // le damos el valor a la variable que se pasa al AlertDialog
                    plantilla = pieChartInput.description
                    // cambiamos el valor para que se muestre el popUp
                    showPopUp = true

                    val tabRotation = currentStartAngle - angleToDraw - 90f
                    rotate(tabRotation){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f,radius*1.2f),
                            color = fondo,
                            cornerRadius = CornerRadius(15f,15f)
                        )
                    }
                    rotate(tabRotation+angleToDraw){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f,radius*1.2f),
                            color = fondo,
                            cornerRadius = CornerRadius(15f,15f)
                        )
                    }


                    //aqui es donde se muestra lo que aparece cuando damos click
                    rotate(rotateAngle){
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                "${pieChartInput.description}",
                                circleCenter.x,
                                circleCenter.y + radius*1.3f*factor,
                                Paint().apply {
                                    textSize = 22.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = Color.Black.toArgb()
                                    isFakeBoldText = true
//                                    setTypeface(textPaint)
                                }
                            )
                        }
                    }
                }
            }

            if(inputList.first().isTapped){
                rotate(-90f){
                    drawRoundRect(
                        topLeft = circleCenter,
                        size = Size(12f,radius*1.2f),
                        color = fondo,
                        cornerRadius = CornerRadius(15f,15f)
                    )
                }
            }


        }
        if(showPopUp){
            popUp(plantilla = plantilla, navController = navController)
        }
    }
}

@Composable
fun popUp(plantilla : String, navController: NavController){
    AlertDialog(onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(onClick = { /* MANDAR A LA PAGINA DE CREAR
                aqui será un if, dependiendo del nombre de la plantilla te
                mandara a su respectiva de creación*/
                navController.navigate(AppScreens.Add_Estudiar.route)
            }) {
                Text(text = "Crear")
            }
        },
        dismissButton = { /** MANDAR A LA PAGINA DE VER EVENTOS POR PLANTILLA
        aqui será un if, dependiendo del nombre de la plantilla te
        mandara a su respectiva de ver eventos totales */
            TextButton(onClick = { /*TODO*/ }) {
                Text( text = "Ver")
            }
        },
        text = {
            Text(text = "¿Qué deseas hacer?")
        })
}

data class PieChartInput(
    val color:Color,
    val value:Int,
    val description:String,
    val isTapped:Boolean = false
)

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectTemplate(){
    val navController = rememberNavController()
    ContentSelectTemplate(navController)
}
