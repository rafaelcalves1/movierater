package com.projects.movierater.features.home


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.projects.movierater.R
import com.projects.movierater.features.home.components.list.MovieError
import com.projects.movierater.features.home.components.list.MovieItem
import com.projects.movierater.features.home.components.list.MovieItemShimmer
import com.projects.movierater.features.home.model.MovieItem
import org.koin.androidx.compose.koinViewModel

private const val GRID_CELL_COUNT = 2

@Composable
fun HomeMovieListScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState: LazyPagingItems<MovieItem> = homeViewModel.uiState.collectAsLazyPagingItems()
    MovieList(
        modifier = Modifier.fillMaxSize(),
        movieList = uiState,
        onRetry = { uiState.refresh() }
    )
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieList: LazyPagingItems<MovieItem>,
    onRetry: () -> Unit
) {
    val context = LocalContext.current

    when (movieList.loadState.refresh) {
        is LoadState.Loading -> {
            MovieListShimmer(modifier = Modifier.fillMaxSize())
        }

        is LoadState.Error -> {
            MovieError(
                modifier = Modifier.fillMaxSize(),
                onRetry = {
                    onRetry.invoke()
                }
            )
        }

        is LoadState.NotLoading -> {
            val commonSpacing = dimensionResource(id = R.dimen.spacing_xxs)
            LazyVerticalGrid(
                columns = GridCells.Fixed(GRID_CELL_COUNT),
                contentPadding = PaddingValues(
                    horizontal = commonSpacing,
                    vertical = commonSpacing
                ),
                verticalArrangement = Arrangement.spacedBy(commonSpacing),
                horizontalArrangement = Arrangement.spacedBy(commonSpacing),
                modifier = modifier.fillMaxSize(),
            ) {
                items(movieList.itemCount) {
                    val movie = movieList[it]!!
                    MovieItem(
                        imgUrl = movie.imgUrlPath,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, movie.movieId.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    )
                }
                movieList.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> {
                            // TODO: Treat Loading Append
                        }

                        is LoadState.Error -> {
                            // TODO: Treat Error -> LoadState retry
                        }

                        is LoadState.NotLoading -> Unit
                    }
                }
            }
        }
    }
}

@Composable
fun MovieListShimmer(modifier: Modifier = Modifier) {
    val commonSpacing = dimensionResource(id = R.dimen.spacing_xxs)
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = commonSpacing, vertical = commonSpacing),
        verticalArrangement = Arrangement.spacedBy(commonSpacing),
        horizontalArrangement = Arrangement.spacedBy(commonSpacing),
        columns = GridCells.Fixed(GRID_CELL_COUNT),
    ) {
        items(8) {
            MovieItemShimmer()
        }
    }
}
