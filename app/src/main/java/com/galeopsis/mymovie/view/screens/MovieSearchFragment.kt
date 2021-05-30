package com.galeopsis.mymovie.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galeopsis.mymovie.databinding.MovieSearchFragmentBinding
import com.galeopsis.mymovie.model.Movie

class MovieSearchFragment : Fragment() {

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    private lateinit var viewModel: MovieSearchViewModel
    private var _binding: MovieSearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                "Test movie",
                "here is the new poster path",
                "25/05/2020",
                "9.9",
                "Nice movie!"
            )
        )
        val adapter = RecycleViewAdapter(movies)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}