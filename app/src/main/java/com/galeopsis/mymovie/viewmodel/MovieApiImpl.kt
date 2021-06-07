package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.Movies

class MovieApiImpl : MovieApi {
    override fun getDataFromServer() = Movies()
    override fun getDataFromLocalStorage() = Movies()
}
