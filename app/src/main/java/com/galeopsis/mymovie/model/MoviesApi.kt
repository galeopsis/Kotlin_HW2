package com.galeopsis.mymovie.model

import com.galeopsis.mymovie.BuildConfig.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=ru-RU

interface MoviesApi {

    fun getDataFromTMDB(): MovieDTO

    @GET("{movieId}?api_key=${API_KEY}&language=ru-RU")
    fun getDataFromTMDB(
        @Path("movieId") movieId: String,
        @Query("page") page: String,
        @Query("vote_average") vote_average: Float
    ): MovieDTO
}
