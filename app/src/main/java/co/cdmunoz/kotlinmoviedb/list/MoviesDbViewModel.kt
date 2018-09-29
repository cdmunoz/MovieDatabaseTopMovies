package co.cdmunoz.kotlinmoviedb.list

import android.arch.lifecycle.ViewModel
import android.util.Log
import co.cdmunoz.kotlinmoviedb.data.MoviesResponse
import co.cdmunoz.kotlinmoviedb.data.source.MovieDbRepository
import co.cdmunoz.kotlinmoviedb.data.source.remote.MovieDbApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDbViewModel : ViewModel() {

    companion object {
        val TAG = MoviesDbViewModel::class.java.name
    }

    private val movieDbRepository = MovieDbRepository(MovieDbApiService.create())
    lateinit var call: Call<MoviesResponse>

    fun loadMovies() {

        call = movieDbRepository.getMovies("2018", "df1b9abfde892d0d5407d6b602b349f2")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "Error getting results: ${t.message}")
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val moviesResponse = response.body()
                val numberOfItems = moviesResponse?.results?.size
                Log.i(TAG, "+++++++++++++++++ Size of results: $numberOfItems")
            }
        })
    }
}