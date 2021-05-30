package com.galeopsis.mymovie.model

class RepositoryImpl : Repository {
    override fun getDataFromServer(): Movies {
        return Movies()
    }

    override fun getDataFromLocalStorage(): Movies {
        return Movies()
    }
}
