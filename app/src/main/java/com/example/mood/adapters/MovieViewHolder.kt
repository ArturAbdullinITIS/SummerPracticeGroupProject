package com.example.mood.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mood.R
import com.example.mood.databinding.ActivityItemMovieBinding
import com.example.mood.model.Movie

class MovieViewHolder(
    private val binding: ActivityItemMovieBinding,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        with(binding) {
            movieTitle.text = "${movie.title} (${movie.year})"



            Glide.with(itemView.context)
                .load(movie.posterUrl)
                .placeholder(R.drawable.movie_placeholder)
                .into(moviePoster)

            root.setOnClickListener { onItemClick(movie) }
        }
    }
}