package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.singleMovie
import retrofit2.Call

sealed class AppState {
    data class Success(val movieData: Call<singleMovie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}