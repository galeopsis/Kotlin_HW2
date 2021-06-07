package com.galeopsis.mymovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val movieApiImpl: MovieApi = MovieApiImpl()
) :
    ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getDataFromLocalSource() = getMoviesFromLocalSource()
    fun getDataFromRemoteSource() = getMoviesFromRemoteSource()

    private fun getMoviesFromRemoteSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(movieApiImpl.getDataFromServer()))
        }.start()
    }

    private fun getMoviesFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(movieApiImpl.getDataFromLocalStorage()))
        }.start()
    }
}
