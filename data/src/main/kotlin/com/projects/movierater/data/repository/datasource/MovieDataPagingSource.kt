package com.projects.movierater.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projects.movierater.data.model.MovieData
import com.projects.movierater.data.network.api.MovieApi
import com.projects.movierater.data.network.model.NetworkResponse

class MovieDataPagingSource(
    private val movieApi: MovieApi
) : PagingSource<Int, MovieData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return kotlin.runCatching {

            val currentPage = params.key ?: 1

            return when (val response = movieApi.getPopularMovie(currentPage)) {
                is NetworkResponse.Success -> {
                    LoadResult.Page(
                        data = response.body.movieList,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (
                            response.body.totalPages == response.body.currentPage ||
                            response.body.movieList.isEmpty()
                        )
                            null
                        else
                            response.body.currentPage + 1
                    )
                }

                is NetworkResponse.ApiError<*> -> LoadResult.Error(response.body)
            }
        }.getOrElse {
            LoadResult.Error(it)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition
    }


}