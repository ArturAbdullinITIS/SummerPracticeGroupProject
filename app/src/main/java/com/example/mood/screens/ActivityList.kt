package com.example.mood.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mood.adapters.MovieAdapter
import com.example.mood.databinding.ActivityListBinding
import com.example.mood.model.Movie
import com.example.mood.network.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityList : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var movieAdapter: MovieAdapter
    private var moviesList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        loadMovies()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter { movie ->
            openMovieDetails(movie)
        }

        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ActivityList)
            adapter = movieAdapter
        }
    }

    private fun loadMovies() {
        val mood = intent.getStringExtra("MOOD_KEY") ?: return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movies = MovieRepository.getMoviesByMood(mood)
                withContext(Dispatchers.Main) {
                    moviesList.clear()
                    moviesList.addAll(movies)
                    movieAdapter.submitList(moviesList)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, ActivityDetail::class.java).apply {
            putExtra("MOVIE_DETAILS", movie)
        }
        startActivity(intent)
    }
}