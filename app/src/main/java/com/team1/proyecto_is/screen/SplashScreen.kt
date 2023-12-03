package com.team1.proyecto_is.screen

import android.window.SplashScreenView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.team1.proyecto_is.R
import com.team1.proyecto_is.navigation.AppScreens
import com.team1.proyecto_is.ui.theme.nunito
import com.team1.proyecto_is.ui.theme.splash
import kotlinx.coroutines.delay
import java.net.ContentHandler

/**
 * La splash screen que mostrará la aplicación
 */
@Composable
fun SplashScreen(navController: NavController){
    
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(AppScreens.SelectTemplate.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(splash)
            .padding(horizontal = 50.dp, vertical = 250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = R.drawable.logo2),
            contentDescription = "",
            Modifier.size(150.dp,150.dp))
        Text(text= "AhoraSí",
            fontSize = 60.sp,
            fontFamily = nunito,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}

