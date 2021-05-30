package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.Movies

sealed class AppState {
    data class Success(val movieData: Movies) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}