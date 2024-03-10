package com.projects.movierater.data.network.api

import com.projects.movierater.data.model.MovieResponse
import com.projects.movierater.data.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") pageNumber: Int,
        @Query("language") language: String = "pt-BR"
    ): NetworkResponse<MovieResponse, Exception>

}