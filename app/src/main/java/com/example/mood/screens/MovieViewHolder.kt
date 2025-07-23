package com.example.mood.screens

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mood.R
import com.example.mood.databinding.ActivityItemMovieBinding


//модель Movie от Гали (поля и название вьюшки поменяю как будет готово)
class MovieViewHolder(
    private val binding: ActivityItemMovieBinding,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        with(binding) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            descriptionTextView.text = movie.plot

            Glide.with(itemView.context)
                .load(movie.posterUrl)
                .placeholder(R.drawable.movie_placeholder)
                .into(posterImageView)

            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}