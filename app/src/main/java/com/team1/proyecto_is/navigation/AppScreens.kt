package com.team1.proyecto_is.navigation

sealed class AppScreens (val route: String){
    object SelectTemplate: AppScreens("select_template")
}