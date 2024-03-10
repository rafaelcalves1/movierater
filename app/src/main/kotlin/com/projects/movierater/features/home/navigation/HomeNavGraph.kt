package com.projects.movierater.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.projects.movierater.features.home.HomeMovieListScreen
import com.projects.movierater.navigation.Routes

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(startDestination = HomeRoutes.MovieList.route, route = Routes.Home.route) {
        composable(HomeRoutes.MovieList.route) {
            HomeMovieListScreen(navController = navController)
        }
    }
}