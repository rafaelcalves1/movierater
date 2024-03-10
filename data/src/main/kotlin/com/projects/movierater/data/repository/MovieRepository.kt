package com.projects.movierater.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.projects.movierater.data.network.api.MovieApi
import com.projects.movierater.data.repository.datasource.MovieDataPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val PAGE_SIZE = 30
private const val PRE_FETCH = 15

class MovieRepository(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPopularMovie() = withContext(dispatcher) {
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PRE_FETCH),
            pagingSourceFactory = {
                MovieDataPagingSource(movieApi)
            }
        ).flow
    }
}