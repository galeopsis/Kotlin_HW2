package com.galeopsis.mymovie.model

data class Movies(
    val defaultMovie: Movie = getDefaultMovie(),
)

fun getDefaultMovie() =
    Movie("The Best Movie Ever !", "poster_path", "release_date", "rating", "overview")