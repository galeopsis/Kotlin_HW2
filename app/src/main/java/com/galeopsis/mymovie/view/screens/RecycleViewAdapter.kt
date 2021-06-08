package com.galeopsis.mymovie.view.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galeopsis.mymovie.R
import com.galeopsis.mymovie.model.Movie

class RecycleViewAdapter(val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = movieList[position]

        holder.searchFragmentTitle.text = movie.title
        holder.searchFragmentReleaseDate.text = movie.releaseDate
        holder.searchFragmentRating.text = movie.rating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchFragmentTitle = itemView.findViewById(R.id.searchFragmentTitle) as TextView
        val searchFragmentReleaseDate = itemView.findViewById(R.id.searchFragmentReleaseDate) as TextView
        val searchFragmentRating = itemView.findViewById(R.id.searchFragmentMovieRating) as TextView
    }
}
