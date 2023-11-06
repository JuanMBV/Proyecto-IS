package com.team1.proyecto_is.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.team1.proyecto_is.R
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.ui.theme.fondo
import com.team1.proyecto_is.ui.theme.nunito
import com.team1.proyecto_is.ui.theme.nunito_bold
import com.team1.proyecto_is.ui.theme.rojo
import java.util.Calendar
import java.util.Date

// variables que usa el método


/**
 * Cosas que faltan de agregar
 * Pop Up que pregunte si desea salir sin guardar (no viene en plantillas)
 * Que los metodos reciban parametros para usarlos en el guardado de datos
 * que el boton de guardado guarde
 * la validacion de los datos para fechas pasadas
 * que muesre un mensaje si se trata de ingresar una fecha pasada.
 */

@Composable
fun Add_Estudiar(navController: NavController){
    ContentAdd_Estudiar(navController)
}

@Composable
fun ContentAdd_Estudiar(navController: NavController) {
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

        InputEstudiar()



    }
}

@Composable
fun InputEstudiar(){
    var text by remember { mutableStateOf("") }
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
            value = text,
            onValueChange = { valor ->
                text = valor
            },
            //aqui se muestra el que texto que indica el cuadro
            label = { Text("") }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        )
        HoraInicioFin()
        Spacer(modifier = Modifier.height(30.dp))
        Fecha()
    }

}


@Composable
fun HoraInicioFin(){
    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val inicio = remember { mutableStateOf("") }
    val fin = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val inicioTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            inicio.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    val finTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            fin.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )


    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Row(/**modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround*/){

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
}


@Composable
fun Fecha(){


    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

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
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
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
        Guardado()
    }
}

@Composable
fun Guardado(){
    // boton de guardado
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom) {
        Button(
            onClick = { /* que guarde los datos TODO */ },
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
 * Estaba haciendo el pop up para cuando intenta salir sin guardar
 * pero vi que no lo puse en las plantillas de especificacion XD
 * entonces me lo salte

fun exitWithoutSaving(navController: NavController){
AlertDialog(onDismissRequest = { /*TODO*/ },
confirmButton = {
TextButton(onClick = { /* si se da click, salir de la pagina*/
navController.navigate(AppScreens.ViewEvents.route)
}) {
Text(text = "Salir")
}
},
dismissButton = { /** Si se indica cancelar operación */
TextButton(onClick = { /**/ }) {
Text( text = "Cancelar")
}
},
text = {
Text(text = "¿Qué deseas hacer?")
})
}*/


@Preview(showSystemUi = true)
@Composable
fun PreviewAdd_Estudiar(){
    val navController = rememberNavController()
    ContentAdd_Estudiar(navController)
}