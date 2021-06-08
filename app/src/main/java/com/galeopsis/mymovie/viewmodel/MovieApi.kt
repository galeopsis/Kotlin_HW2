package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.singleMovie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET("791373?api_key=0d0ed127fc09b25b0e6459e907895456&language=ru-RU")
    fun getDataFromServer(): Call<singleMovie>
}
