package com.galeopsis.mymovie.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.galeopsis.mymovie.R
import com.galeopsis.mymovie.databinding.MainFragmentBinding
import com.galeopsis.mymovie.model.singleMovie
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

        setRemoteMovieData()
        binding.btnOverview.setOnClickListener { goToSearchFragment() }
    }

    private fun goToSearchFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MovieSearchFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData
                binding.loadingLayout.visibility = View.GONE
                setRemoteMovieData()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { setRemoteMovieData() })
            }
        }
    }

    private fun setRemoteMovieData() {
        with(binding) {

            movieTitle.visibility = View.INVISIBLE
            releaseDate.visibility = View.INVISIBLE
            movieOverview.visibility = View.INVISIBLE
            movieRating.visibility = View.INVISIBLE

            setPoster()

            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val response = api.getDataFromServer().awaitResponse()
                if (response.isSuccessful) {
                    val data: singleMovie = response.body()!!
                    Log.d("My Movie", data.overview)

                    withContext(Dispatchers.Main) {

                        movieTitle.visibility = View.VISIBLE
                        releaseDate.visibility = View.VISIBLE
                        movieOverview.visibility = View.VISIBLE
                        movieRating.visibility = View.VISIBLE

                        movieTitle.text = data.title
                        movieRating.text = data.vote_average.toString()
                        movieOverview.text = data.overview
                        releaseDate.text = data.release_date
                    }
                }
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    private fun setPoster() {
        val posterPath = "/mUKyopnIdWe8bqW7Q4VSiQEDaeq.jpg"

        Glide.with(this)
            .load(IMAGE_BASE_URL + posterPath)
            .into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}