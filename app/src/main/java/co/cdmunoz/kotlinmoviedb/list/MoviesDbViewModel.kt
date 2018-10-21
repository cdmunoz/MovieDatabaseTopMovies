package co.cdmunoz.kotlinmoviedb.list

import android.arch.lifecycle.ViewModel
import android.util.Log
import co.cdmunoz.kotlinmoviedb.MoviesDbApplication
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import co.cdmunoz.kotlinmoviedb.data.MoviesResponse
import co.cdmunoz.kotlinmoviedb.data.source.MovieDbRepository
import co.cdmunoz.kotlinmoviedb.data.source.local.MoviesDatabase
import co.cdmunoz.kotlinmoviedb.data.source.remote.MovieDbApiService
import co.cdmunoz.kotlinmoviedb.utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MoviesDbViewModel : ViewModel() {

    companion object {
        val TAG = MoviesDbViewModel::class.java.name
    }

    private val database = MoviesDatabase.getMoviesDbDatabase(MoviesDbApplication.instance)
    private val movieDbRepository: MovieDbRepository = MovieDbRepository(MovieDbApiService.create(),
            database.moviesDbDao())

    private val context = MoviesDbApplication.instance

    lateinit var call: Call<MoviesResponse>

    fun loadMovies() {
        val isConnected = Utilities.isConnectionAvailable(MoviesDbApplication.instance)
        var hasApiData = Utilities.shouldGetDataFromApi(context)
        if (isConnected && hasApiData) {
            loadMoviesFromApi()
            Utilities.putLongSharedPreferences(context, "pref_time_net_query",
                    Calendar.getInstance().timeInMillis)
        }
    }

    private fun loadMoviesFromApi() {
        call = movieDbRepository.getMoviesFromApi("2018", "df1b9abfde892d0d5407d6b602b349f2")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "Error getting results from API: ${t.message}")
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val moviesResponse = response.body()
                val numberOfItems = moviesResponse?.results?.size
                Log.i(TAG, "+++++++++++++++++ Size of results: $numberOfItems")
                val movies: List<MovieItem>? = moviesResponse?.results
                if (movies != null) {
                    for (movieItem in movies) {
                        movieDbRepository.insertMovieIntoDb(movieItem)
                    }
                }
            }
        })
    }

    fun getMovies() = movieDbRepository.getMoviesFromDb()
}