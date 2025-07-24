package network
import model.Movie
import model.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") search: String
    ): MovieSearchResponse
}