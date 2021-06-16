package com.galeopsis.mymovie.viewmodel

import com.galeopsis.mymovie.model.SingleMovie

class MovieApiImpl : MovieApiInterface {
    override fun getDataFromServer() =
        SingleMovie("movie", "01.01.2021", 9.9, "nice movie", "remote_path")
}