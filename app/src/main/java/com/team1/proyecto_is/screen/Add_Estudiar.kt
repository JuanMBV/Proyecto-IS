package com.team1.proyecto_is.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.R
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.service.EventosService
import com.team1.proyecto_is.ui.theme.fondo
import com.team1.proyecto_is.ui.theme.nunito
import com.team1.proyecto_is.ui.theme.nunito_bold
import com.team1.proyecto_is.ui.theme.rojo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar
import java.util.Date

// variables que usa el método


/**
 * Cosas que faltan de agregar
 * chequeo de las fechas (que no permita fechas anterioores)
 * chequeo de las horas (no permitir misma hora en inicio y fin) (FALTA PROBAR)
 * el textInput deja un valor en la casilla (no queda totalmente vacia) (FALTA PROBAR)
 * checar el regex, que permita espacios, comas, puntos, dos puntos, acentos (FALTA PROBAR)
 * chwcar el + al final del regex, cambiarlo por * (0 o mas) (FALTA PROBAR)
 * cambiar el color de la imagen de boton de completdos
 * eliminar boton de hora de fin y dejar solo HoraInicio (hora) y dia.
 */

@Composable
fun Add_Estudiar(navController: NavController, dataBase: DataBase){
    ContentAdd_Estudiar(navController, dataBase)
}


@Composable
fun ContentAdd_Estudiar(navController: NavController,dataBase: DataBase) {
    Column(
        modifier = Modifier
            .background(fondo)
            .fillMaxSize()
            .padding(start = 28.dp, top = 15.dp, end = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        // cajita para escribir el texto de "Nuevo evento de"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ){
            Text(
                text = "Nuevo evento de",
                fontSize = 35.sp,
                fontFamily = nunito,
            )
            // boton de cerrado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.ViewEvents.route)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_close),
                        contentDescription = ""
                    )
                }
            }
        }
        // texto de "Estudiar"
        Text(
            text = "Estudiar ",
            fontSize = 45.sp,
            textAlign = TextAlign.Start,
            fontFamily = nunito_bold,
            color = rojo
        )
        //espacio entre texto y el input
        Spacer(modifier = Modifier.padding(top = 20.dp))

        // inicio de seccion de campo de texto
        var entrada by remember { mutableStateOf("") }
        val mContext = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Materia: ",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontFamily = nunito_bold,
            )


            // campo de texto
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = entrada,
                onValueChange = { valor ->
                    if(valor.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ!¡¿?\\-: ]*".toRegex())) {
                        entrada = valor
                    } else{
                        Toast.makeText(mContext, "Dato no alfanumerico", Toast.LENGTH_SHORT).show();
                    }

                },
                //aqui se muestra el que texto que indica el cuadro
                label = { Text("") }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            )

            // fin de seccion de campo de texto

            // inicio de seccion de campo de horas de inicio / fin

            // Fetching local context

            // Declaring and initializing a calendar
            val mCalendar = Calendar.getInstance()
            val mHour = mCalendar[Calendar.HOUR_OF_DAY]
            val mMinute = mCalendar[Calendar.MINUTE]

            val range = 0..9

            // Value for storing time as a string
            val inicio = remember { mutableStateOf("") }
            val fin = remember { mutableStateOf("") }

            // Creating a TimePicker dialod
            val inicioTimePickerDialog = TimePickerDialog(
                mContext,
                {_, mHour : Int, mMinute: Int ->

                    // si la hora es 0 y el min esta entre 1 y 9
                    // O si la hora esta entre 1 y 9 y el min es 0
                    if(range.contains(mHour)&&range.contains(mMinute)){
                        inicio.value = "0$mHour:0$mMinute"
                    }
                    // si el minuto esta entre 1 y 9, agregale el 0 antes
                    else if(range.contains(mMinute)){
                        inicio.value = "$mHour:0$mMinute"
                    }
                    // si la hora esta entre 1 y 9, agregale el 0 antes
                    else if(range.contains(mHour)){
                        inicio.value = "0$mHour:$mMinute"
                    }
                    // si ambos son de dos cifras
                    else{
                        inicio.value = "$mHour:$mMinute"
                    }

                }, mHour, mMinute, false
            )

            val finTimePickerDialog = TimePickerDialog(
                mContext,
                {_, mHour : Int, mMinute: Int ->

                    // si la hora es 0 y el min esta entre 1 y 9
                    // O si la hora esta entre 1 y 9 y el min es 0
                    if(range.contains(mHour)&&range.contains(mMinute)){
                        fin.value = "0$mHour:0$mMinute"
                    }
                    // si el minuto esta entre 1 y 9, agregale el 0 antes
                    else if(range.contains(mMinute)){
                        fin.value = "$mHour:0$mMinute"
                    }
                    // si la hora esta entre 1 y 9, agregale el 0 antes
                    else if(range.contains(mHour)){
                        fin.value = "0$mHour:$mMinute"
                    }
                    // si ambos son de dos cifras
                    else{
                        fin.value = "$mHour:$mMinute"
                    }
                }, mHour, mMinute, false
            )


            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Row(){
                    Button(onClick = { inicioTimePickerDialog.show() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)
                        )) {
                        Icon(
                            painter = painterResource(R.drawable.icon_bell),
                            contentDescription = "",
                            modifier = Modifier.width(20.dp)
                        )
                        Text(text = "  Hora Inicio",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = nunito)
                    }

                    Row(){
                        // Add a spacer of 100dp
                        Spacer(modifier = Modifier.width(15.dp))

                        Button(onClick = { finTimePickerDialog.show() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)
                            )) {
                            Icon(
                                painter = painterResource(R.drawable.icon_ringingbell),
                                contentDescription = "",
                                modifier = Modifier.width(20.dp)
                            )
                            Text(text = "  Hora Fin",
                                color = Color.White,
                                fontFamily = nunito,
                                fontSize = 20.sp)
                        }
                    }
                }
            }


            Column(modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround){

                    // Display selected time
                    Text(text = " ${inicio.value}",
                        fontSize = 20.sp,
                        fontFamily = nunito)

                    // Add a spacer of 100dp
                    Spacer(modifier = Modifier.width(25.dp))

                    // Display selected time
                    Text(text = "${fin.value}",
                        fontSize = 20.sp,
                        fontFamily = nunito)
                }
            }

            // fin de seccion de campo de horas de inicio / fin
            Spacer(modifier = Modifier.height(30.dp))

            // inicio de sección de selección de fecha
            // Fetching the Local Context
            val mmContext = LocalContext.current

            // Declaring integer values
            // for year, month and day
            val mYear: Int
            val mMonth: Int
            val mDay: Int


            // Fetching current year, month and day
            mYear = mCalendar.get(Calendar.YEAR)
            mMonth = mCalendar.get(Calendar.MONTH)
            mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

            mCalendar.time = Date()

            // Declaring a string value to
            // store date in string format
            val mDate = remember { mutableStateOf("") }

            // Declaring DatePickerDialog and setting
            // initial values as current values (present year, month and day)
            val mDatePickerDialog = DatePickerDialog(
                mmContext,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    // si el dia es un numero solo Y el mes tmbn
                    if(range.contains(mDayOfMonth)&&range.contains(mMonth)){
                        mDate.value = "0$mDayOfMonth/0${mMonth+1}/$mYear"
                    }
                    // si el dia es solo un numero
                    else if(range.contains(mDayOfMonth)){
                        mDate.value = "0$mDayOfMonth/${mMonth+1}/$mYear"
                    }
                    // si el mes es solo un numero
                    else if(range.contains(mMonth)){
                        mDate.value = "$mDayOfMonth/0${mMonth+1}/$mYear"
                    }
                    // si ambos son dos números
                    else {
                        mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
                    }
                }, mYear, mMonth, mDay
            )

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                // Creating a button that on
                // click displays/shows the DatePickerDialog

                Button(onClick = { mDatePickerDialog.show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)
                    )) {
                    Icon(
                        painter = painterResource(R.drawable.icon_calendar),
                        contentDescription = "",
                        modifier = Modifier.width(20.dp)
                    )
                    Text(text = "  Escoge el día :D",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontFamily = nunito)
                }

                // Adding a space of 100dp height
                Spacer(modifier = Modifier.height(30.dp))

                Text(text = "${mDate.value}",
                    fontSize = 20.sp,
                    fontFamily = nunito)
                Guardado(navController, dataBase, entrada.toString(), inicio.value, fin.value, mDate.value)
            }
            // fin de sección de selección de fecha



        }
    }
}


@Composable
fun Guardado(navController: NavController, dataBase: DataBase, text : String, inicio : String, fin : String, date : String){

    // boton de guardado
    val eventosService = EventosService(dataBase)
    val contexto = LocalContext.current
    val gInicio = "$date $inicio:00"
    val gFin = "$date $fin:00"
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom) {
        Button(
            onClick = { /* que guarde los datos TODO */
                if (text == "" || inicio == "" || fin == "" || date == "") {
                    Toast.makeText(contexto, "Faltan datos", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val formatInicio = LocalDateTime.parse(gInicio, formatter)
                        val formatFin = LocalDateTime.parse(gFin, formatter)
                        if(formatFin.isBefore(formatInicio)){
                            Toast.makeText(contexto, "Verifique su hora de fin", Toast.LENGTH_SHORT).show()
                        }
                        Log.d("Correcto", "Se convirtio correctamente el texto")
                        eventosService.InsertEvento(
                            1,
                            text.toString(),
                            null,
                            null,
                            null,
                            null,
                            0,
                            LocalDateTime.now(),
                            formatInicio,
                            formatFin,
                            null
                        )
                        Log.d("NOTA","Ya agrego el evento")
                        Toast.makeText(contexto, "Evento agregado", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                        navController.navigate(AppScreens.ViewEvents.route)
                    } catch (e: DateTimeParseException) {
                        Toast.makeText(contexto, "Algo salió mal", Toast.LENGTH_SHORT).show()
                        println("Invalid date-time string: $gInicio / $gFin")
                        Log.d("Error al convertir texto", e.toString())
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = rojo)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_check),
                contentDescription = "",
                modifier = Modifier.width(20.dp),
            )
            Text(
                text = "  Guardar ",
                fontSize = 20.sp,
                fontFamily = nunito_bold,
            )
        }
    }
}


/**


@Preview(showSystemUi = true)
@Composable
fun PreviewAdd_Estudiar(){
    val navController = rememberNavController()
    ContentAdd_Estudiar(navController)
}
*/
