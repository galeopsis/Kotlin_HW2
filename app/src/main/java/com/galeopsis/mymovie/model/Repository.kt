package com.galeopsis.mymovie.model

interface Repository {
    fun getDataFromServer(): Movies
    fun getDataFromLocalStorage(): Movies
}
