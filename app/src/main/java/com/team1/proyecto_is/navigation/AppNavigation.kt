package com.team1.proyecto_is.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team1.proyecto_is.screen.SelectTemplate
import com.team1.proyecto_is.screen.ViewEvents

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SelectTemplate.route){
        composable(route = AppScreens.SelectTemplate.route){
            SelectTemplate(navController)
        }
        composable(route = AppScreens.ViewEvents.route){
            ViewEvents(navController)
        }
    }
}