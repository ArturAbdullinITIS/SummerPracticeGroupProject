package network


import model.Movie
import model.MovieSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val API_KEY = "5772822b"

    private val api: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    suspend fun getMoviesByMood(mood: String): List<Movie> {
        val response: MovieSearchResponse = api.getMovies(API_KEY, mood)
        return response.Search
    }
}
