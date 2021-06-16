package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.SingleMovie

sealed class AppState {
    data class Success(val movieData: SingleMovie) : AppState()
    data class Error(val error: Throwable? = null) : AppState()
    object Loading : AppState()
}