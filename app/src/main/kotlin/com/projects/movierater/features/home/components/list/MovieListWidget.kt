package com.projects.movierater.features.home.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.projects.movierater.R
import com.projects.movierater.features.home.model.MovieItem

@Composable
fun MovieListWidget(
    modifier: Modifier = Modifier,
    movieList: LazyPagingItems<MovieItem>,
    onMovieClick: (movieId: Int) -> Unit,
    onRetryClick: () -> Unit = {}
) {
    when (movieList.loadState.refresh) {
        is LoadState.Loading -> {
            MovieListShimmer(modifier = Modifier.fillMaxSize())
        }

        is LoadState.Error -> {
            // TODO: Treat Error -> onRetryClick.invoke()
        }

        is LoadState.NotLoading -> {
            val commonSpacing = dimensionResource(id = R.dimen.spacing_xxs)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    horizontal = commonSpacing,
                    vertical = commonSpacing
                ),
                verticalArrangement = Arrangement.spacedBy(commonSpacing),
                horizontalArrangement = Arrangement.spacedBy(commonSpacing),
                modifier = modifier,
            ) {
                items(movieList.itemCount) {
                    val movie = movieList[it]!!
                    MovieItem(
                        imgUrl = movie.imgUrlPath,
                        modifier = Modifier.clickable {
                            onMovieClick.invoke(movie.movieId)
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
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(8) {
            MovieItemShimmer()
        }
    }
}