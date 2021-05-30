package com.galeopsis.mymovie.view.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galeopsis.mymovie.R
import com.galeopsis.mymovie.model.Movie

class RecycleViewAdapter(val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = movieList[position]

        holder.textViewName.text = movie.title
        holder.textViewName.text = movie.releaseDate
        holder.textViewName.text = movie.rating
        holder.textViewName.text = movie.posterPath
        holder.textViewName.text = movie.overview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.SearchTextView) as TextView
        val imageViewName = itemView.findViewById(R.id.searchImageView) as ImageView
        val textViewName2 = itemView.findViewById(R.id.SearchTextView2) as TextView
        val imageViewName2 = itemView.findViewById(R.id.searchImageView2) as ImageView

    }
}