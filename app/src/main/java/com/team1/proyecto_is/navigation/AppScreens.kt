package com.team1.proyecto_is.navigation

sealed class AppScreens (val route: String) {
    object SelectTemplate : AppScreens("select_template")
    object ViewEvents : AppScreens("view_events")
    object SplashScreen : AppScreens("splash_screen")
    object Add_Estudiar : AppScreens("add_estudiar")
    object Add_Hobby : AppScreens("add_hobby")
    object ViewEventsByPlantilla : AppScreens("view_events_by_plantilla")
    object ViewEventsCompleted : AppScreens("view_events_completed")

}
