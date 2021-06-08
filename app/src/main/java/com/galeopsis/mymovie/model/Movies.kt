package com.galeopsis.mymovie.model

data class Movies(
    val defaultMovie: singleMovie = getDefaultMovie(),
)

fun getDefaultMovie() =
    singleMovie("The Best Movie Ever !", "01.01.2021", 9.1, "very nice movie! Отвечаю!")