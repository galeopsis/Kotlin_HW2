package com.galeopsis.mymovie.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.galeopsis.mymovie.R
import com.galeopsis.mymovie.databinding.MainFragmentBinding
import com.galeopsis.mymovie.model.Movies
import com.galeopsis.mymovie.viewmodel.AppState
import com.galeopsis.mymovie.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)

        })
        viewModel.getDataFromLocalSource()
        binding.btnOverview.setOnClickListener { goToSearchFragment() }
    }

    private fun goToSearchFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MovieSearchFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit();
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData
                binding.loadingLayout.visibility = View.GONE
                setData(movieData)
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getDataFromLocalSource() }
                    .show()
            }
        }
    }

    private fun showSnackText() {
        Snackbar
            .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
            .setAction("Reload") { viewModel.getDataFromLocalSource() }
            .show()
    }

    private fun setData(movieData: Movies) {
        setDefaultMovie()
    }

    private fun setDefaultMovie() {
        binding.movieTitle.text = "Лига справедливости Зака Снайдера"
        binding.movieRating.text = "Рейтинг: 8.5"
        binding.releaseDate.text = getString(R.string.default_release_date)
        binding.movieOverview.text = getString(R.string.default_overview)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}