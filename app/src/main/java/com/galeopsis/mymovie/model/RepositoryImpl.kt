package com.galeopsis.mymovie.model

class RepositoryImpl : Repository {
    override fun getDataFromServer() = Movies()
    override fun getDataFromLocalStorage() = Movies()
}
