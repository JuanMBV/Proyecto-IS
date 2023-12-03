package com.team1.proyecto_is.screen



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.team1.proyecto_is.MainActivity
import com.team1.proyecto_is.R
import com.team1.proyecto_is.component.ChooseColor
import com.team1.proyecto_is.component.EventsListCompleted
import com.team1.proyecto_is.component.EventsListPlantilla
import com.team1.proyecto_is.component.ListItemRow
import com.team1.proyecto_is.component.idPlantillaGlobal
import com.team1.proyecto_is.component.plantillaGlobal
import com.team1.proyecto_is.component.sendIdPlantilla
import com.team1.proyecto_is.model.*
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.service.*
import com.team1.proyecto_is.ui.theme.*
import com.team1.proyecto_is.screen.EventsList



@Composable
fun ViewEventsCompleted(navController: NavController, dataBase: DataBase) {
    ContentViewEventsCompleted(navController, dataBase)
}

@Composable
fun ContentViewEventsCompleted(navController: NavController, dataBase: DataBase) {
    Column(
        modifier = Modifier
            .background(fondo)
            .fillMaxSize()
            .padding(start = 28.dp, top = 15.dp, end = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ){
            Text(
                text = "Eventos",
                fontSize = 35.sp,
                fontFamily = nunito,
            )
            // boton de cerrado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.navigate(AppScreens.SelectTemplate.route)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_close),
                        contentDescription = ""
                    )
                }
            }
        }
        // texto de "Completados"
        Text(
            text = "Completados",
            fontSize = 45.sp,
            textAlign = TextAlign.Start,
            fontFamily = nunito_bold,
            color = verde,
        )
    }
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 160.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top,

        ){
        Row() {
            EventsListCompleted(dataBase)
        }
    }
}


