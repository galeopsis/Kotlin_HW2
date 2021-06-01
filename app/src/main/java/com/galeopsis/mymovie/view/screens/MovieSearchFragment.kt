package com.galeopsis.mymovie.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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

        val recyclerView: RecyclerView = binding.MyRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)

        /* val defaultMovies = ArrayList<Movie>()

         defaultMovies.add(

             Movie(
                 "Лига справедливости Зака Снайдера",
                 "2",
                 "18.03.2021 г.",
                 "8.5",
                 "Вдохновившись самопожертвованием Супермена, Брюс Уэйн вновь обретает веру в человечество. Он заручается поддержкой новой союзницы Дианы Принс, чтобы сразиться с ещё более могущественным противником. Бэтмен и Чудо-женщина набирают команду сверхлюдей для борьбы с пробудившейся угрозой."
             )*/
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                "Лига справедливости Зака Снайдера",
                "2",
                "18.03.2021 г.",
                "8.5",
                "Вдохновившись самопожертвованием Супермена, Брюс Уэйн вновь обретает веру в человечество. Он заручается поддержкой новой союзницы Дианы Принс, чтобы сразиться с ещё более могущественным противником. Бэтмен и Чудо-женщина набирают команду сверхлюдей для борьбы с пробудившейся угрозой."
            )
        )

        val adapter = RecycleViewAdapter(movies)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}