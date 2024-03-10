package com.projects.movierater.features.home


import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.projects.movierater.features.home.model.MovieItem
import com.projects.movierater.features.home.components.list.MovieListWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeMovieListScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState: LazyPagingItems<MovieItem> = homeViewModel.uiState.collectAsLazyPagingItems()
    val context = LocalContext.current

    MovieListWidget(
        modifier = Modifier.fillMaxSize(),
        movieList = uiState,
        onRetryClick = {
            homeViewModel.fetchMovieList()
        },
        onMovieClick = {
            // TODO: Implement navigate to Movie Detail, if had time
            Toast.makeText(context, "Oia filme ${it}", Toast.LENGTH_SHORT).show()
        }
    )
}

