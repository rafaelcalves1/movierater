package com.projects.movierater.features.home.navigation

sealed class HomeRoutes(
    val route: String
){

    object MovieList: HomeRoutes("MovieList")
}
