package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.Movies

interface MovieApi {
    fun getDataFromServer(): Movies
    fun getDataFromLocalStorage(): Movies
}
