package com.galeopsis.mymovie.utils

import com.galeopsis.mymovie.BuildConfig

class Credentials {

    val BASE_URL = "https://api.themoviedb.org/3/movie/"
    var MovieLanguage: String = "ru-RU"
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
    var apikey = BuildConfig.API_KEY
}