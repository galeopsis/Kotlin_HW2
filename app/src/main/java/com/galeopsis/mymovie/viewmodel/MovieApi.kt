package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.SingleMovie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET("460465?api_key=0d0ed127fc09b25b0e6459e907895456&language=ru-RU")
    fun getDataFromServer(): Call<SingleMovie>
}
