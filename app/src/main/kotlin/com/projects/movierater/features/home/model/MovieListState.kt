package com.projects.movierater.features.home.model

import androidx.paging.PagingData
import com.projects.movierater.data.model.MovieData

data class MovieListState(
    val isLoading: Boolean = true,
    val pagingData: PagingData<MovieItem> = PagingData.empty()
)

data class MovieItem(
    val imgUrlPath: String,
    val movieId: Int
) {
    constructor(response: MovieData) : this(
        imgUrlPath = response.posterImgPath,
        movieId = response.movieId
    )
}