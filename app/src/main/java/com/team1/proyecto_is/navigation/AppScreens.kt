package com.team1.proyecto_is.navigation

sealed class AppScreens (val route: String) {
    object SelectTemplate : AppScreens("select_template")
    object ViewEvents : AppScreens("view_events")
    object SplashScreen : AppScreens("splash_screen")
}
