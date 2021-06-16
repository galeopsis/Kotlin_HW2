package com.galeopsis.mymovie.view.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.galeopsis.mymovie.R
import com.galeopsis.mymovie.databinding.MainFragmentBinding
import com.galeopsis.mymovie.model.SingleMovie
import com.galeopsis.mymovie.viewmodel.AppState
import com.galeopsis.mymovie.viewmodel.MovieApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/movie/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getDataFromRemoteSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData

                binding.loadingLayout.visibility = View.GONE
                setRemoteMovieData(movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getDataFromRemoteSource() })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                Log.d("API123", "done")
                true
            }
            R.id.action_main -> {
                Log.d("API123", "done")
                goToSearchFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToSearchFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MovieSearchFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setRemoteMovieData(movieData: SingleMovie) {

        with(binding) {

            loadingLayout.visibility = View.VISIBLE
            movieTitle.visibility = View.INVISIBLE
            releaseDate.visibility = View.INVISIBLE
            movieOverview.visibility = View.INVISIBLE
            movieRating.visibility = View.INVISIBLE
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val response = api.getDataFromServer().awaitResponse()
                when {
                    response.isSuccessful -> {
                        val data: SingleMovie = response.body()!!

                        withContext(Dispatchers.Main) {

                            loadingLayout.visibility = View.GONE
                            movieTitle.visibility = View.VISIBLE
                            releaseDate.visibility = View.VISIBLE
                            movieOverview.visibility = View.VISIBLE
                            movieRating.visibility = View.VISIBLE

                            movieTitle.text = data.title
                            movieRating.text = data.vote_average.toString()
                            movieOverview.text = data.overview
                            releaseDate.text = data.release_date
                            setPoster(data)
                        }
                    }
                    else -> {

                        binding.loadingLayout.visibility = View.GONE
                        binding.mainView.showSnackBar(
                            getString(R.string.error),
                            getString(R.string.reload),
                            { setRemoteMovieData(movieData) })
                    }
                }
            }
        }
    }

    private fun setPoster(data: SingleMovie) {
        val posterPath = IMAGE_BASE_URL + data.poster_path

        Glide.with(this)
            .load(posterPath)
            .into(binding.imageView)
    }


    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
