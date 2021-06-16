package com.galeopsis.mymovie.view.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galeopsis.mymovie.viewmodel.AppState
import com.galeopsis.mymovie.viewmodel.MovieApiImpl
import com.galeopsis.mymovie.viewmodel.MovieApiInterface

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mApi: MovieApiInterface = MovieApiImpl()
) :
    ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getDataFromRemoteSource() = getMoviesFromRemoteSource()

    private fun getMoviesFromRemoteSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(mApi.getDataFromServer())
            )
        }.start()
    }
}