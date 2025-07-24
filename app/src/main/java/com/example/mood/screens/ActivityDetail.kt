package com.example.mood.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mood.R
import com.example.mood.databinding.ActivityDetailBinding
import com.example.mood.model.Movie

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = intent.getParcelableExtra<Movie>("MOVIE_DETAILS")
        movie?.let { displayMovieDetails(it) }

        binding.btnTrailer.setOnClickListener {
            val query = "${movie?.title} trailer"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=$query"))
            startActivity(intent)
        }
    }

    private fun displayMovieDetails(movie: Movie) {
        with(binding) {
            Glide.with(this@ActivityDetail)
                .load(movie.posterUrl)
                .placeholder(R.drawable.movie_placeholder)
                .into(movieBackdrop)

            movieTitle.text = movie.title
            movieYear.text = movie.year.toString()
            movieDescription.text = movie.plot
        }
    }
}