package com.galeopsis.mymovie.model

data class SingleMovie(
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val overview: String,
    val poster_path: String
)