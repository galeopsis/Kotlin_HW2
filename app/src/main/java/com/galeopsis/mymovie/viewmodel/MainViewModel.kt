package com.galeopsis.mymovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galeopsis.mymovie.model.Repository
import com.galeopsis.mymovie.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getDataFromLocalSource() = getMoviesFromLocalSource()
    fun getDataFromRemoteSource() = getMoviesFromLocalSource()
    private fun getMoviesFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getDataFromLocalStorage()))
        }.start()
    }
}
