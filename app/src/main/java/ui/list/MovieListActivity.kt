package ui.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mood.databinding.ActivityListBinding

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val moodKey = intent.getStringExtra("MOOD_KEY") ?: "happy"

        viewModel.movies.observe(this, Observer { movies ->
            val adapter = MovieAdapter(movies) { selectedMovie ->
            }
            binding.recyclerView.adapter = adapter
        })

        viewModel.loadMoviesByMood(moodKey)
    }
}
