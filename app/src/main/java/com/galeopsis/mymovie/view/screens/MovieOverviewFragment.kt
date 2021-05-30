package com.galeopsis.mymovie.view.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galeopsis.mymovie.R

class MovieOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = MovieOverviewFragment()
    }

    private lateinit var viewModel: MovieOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_overview_fragment, container, false)
    }



}