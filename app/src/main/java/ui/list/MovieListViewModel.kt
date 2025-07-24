package ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import kotlinx.coroutines.launch
import model.Movie
import network.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun loadMoviesByMood(moodKey: String) {
        viewModelScope.launch {
            val result = repository.getMoviesByMood(moodKey)
            _movies.value = result
        }
    }
}
