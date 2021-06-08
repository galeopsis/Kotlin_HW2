package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.SingleMovie
import retrofit2.Call

sealed class AppState {
    data class Success(val movieData: Call<SingleMovie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}