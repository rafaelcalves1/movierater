package com.projects.movierater.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieResponse(
    @Json(name = "page") val currentPage: Int,
    @Json(name = "results") val movieList: List<MovieData>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
class MovieData(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropImgPath: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "id") val movieId: Int,
    @Json(name = "original_language") val originalMovieLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterImgPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAvetage: Double,
    @Json(name = "vote_count") val voteCount: Int
)

