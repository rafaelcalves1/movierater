package com.projects.movierater.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.projects.movierater.data.repository.MovieRepository
import com.projects.movierater.features.home.model.MovieItem
import com.projects.movierater.features.home.model.MovieListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PagingData<MovieItem>> =
        MutableStateFlow(
            PagingData.empty(
                LoadStates(
                    refresh = LoadState.Loading,
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            )
        )

    val uiState: StateFlow<PagingData<MovieItem>>
        get() = _uiState.asStateFlow()

    init {
        fetchMovieList()
    }

    fun fetchMovieList() {
        viewModelScope.launch {
            repository.getPopularMovie().cachedIn(viewModelScope).collectLatest { page ->
                _uiState.value = page.map { MovieItem(it) }
            }
        }
    }
}