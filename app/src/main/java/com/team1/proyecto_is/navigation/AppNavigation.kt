package com.team1.proyecto_is.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.screen.SelectTemplate
import com.team1.proyecto_is.screen.SplashScreen
import com.team1.proyecto_is.screen.ViewEvents


@Composable
fun AppNavigation(dataBase: DataBase){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){
        composable(route = AppScreens.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = AppScreens.SelectTemplate.route){
            SelectTemplate(navController)
        }
        composable(route = AppScreens.ViewEvents.route){
            ViewEvents(navController,dataBase)
        }
    }
}