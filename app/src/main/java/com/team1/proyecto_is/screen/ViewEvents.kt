package com.team1.proyecto_is.screen



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.team1.proyecto_is.component.ChooseText
import com.team1.proyecto_is.component.ListItemRow
import com.team1.proyecto_is.model.*
import com.team1.proyecto_is.service.*
import com.team1.proyecto_is.ui.theme.*



@Composable
fun ViewEvents(navController: NavController, dataBase: DataBase) {
    ContentViewEvents(dataBase)
}

@Composable
fun ContentViewEvents(dataBase: DataBase) {
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
                textAlign = TextAlign.Justify,
                fontFamily = nunito
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = { /* nos va a mandar a pagina de completados*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_check_green),
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
            EventsList(dataBase)
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsList(dataBase: DataBase){
    //val popup = remember { mutableStateOf(false) }
    val eventosService = EventosService(dataBase)
// Cambia la declaración de listEvents
    val listEvents = remember { mutableStateListOf<Eventos>() }
    listEvents.clear() // Limpia la lista antes de agregar nuevos eventos
// Llena la lista con los elementos de eventosService.SelectAllEvents()
    listEvents.addAll(eventosService.SelectAllEvents())
    val contexto = LocalContext.current
    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        // por cada item de la lista de objetos listEvents
        items(listEvents, key = { listEvents -> listEvents.hashCode()}) {
                item ->
            val state = rememberDismissState(
                confirmValueChange = {
                    when(it){
                        DismissValue.DismissedToEnd ->{
                            // para completar el evento (izquierda a derecha)
                            eventosService.CompleteEvent(item.getIdEventos())
                            listEvents.remove(item)
                            Toast.makeText(contexto, "Completado", Toast.LENGTH_SHORT).show()
                            //}
                        }
                        DismissValue.DismissedToStart ->{
                            //para eliminar el evento (derecha a izquierda)
                            eventosService.DeleteEvent(item.getIdEventos())
                            listEvents.remove(item)
                            Toast.makeText(contexto, "Eliminado", Toast.LENGTH_SHORT).show()
                        }
                        DismissValue.Default->{
                            // cuando lo deja a medias
                        }
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color =
                        when(state.dismissDirection){
                            DismissDirection.EndToStart -> Color.Transparent
                            DismissDirection.StartToEnd -> Color.Transparent
                            null -> Color.Transparent
                        }
                },
                dismissContent = {
                    ListItemRow(item)
                },
            )

        }
    }
}





// programacion

@Preview(showSystemUi = true)
@Composable
fun PreviewViewEvents() {
    val mainActivity = MainActivity()
    ContentViewEvents(mainActivity.InitializeDatabaseConnection(LocalContext.current))
}