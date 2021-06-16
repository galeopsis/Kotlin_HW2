package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.SingleMovie

interface MovieApiInterface {
    fun getDataFromServer(): SingleMovie
}