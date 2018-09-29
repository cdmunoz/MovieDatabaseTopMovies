package co.cdmunoz.kotlinmoviedb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import co.cdmunoz.kotlinmoviedb.api.MovieDbApiService
import co.cdmunoz.kotlinmoviedb.api.MoviesResponse
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    companion object {
        val TAG = ListActivity::class.java.name
    }

    lateinit var apiService: MovieDbApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        apiService = MovieDbApiService.create()

        val call = getMovies("2018", "df1b9abfde892d0d5407d6b602b349f2")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "Error getting results: ${t.message}")
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val moviesResponse = response.body()
                val numberOfItems = moviesResponse?.results?.size
                Log.i(TAG, "+++++++++++++++++ Size of results: $numberOfItems")
                hello_text_view.text = "Items: ${moviesResponse?.results.toString()}"
            }
        })
    }

    fun getMovies(year: String, apiKey: String): Call<MoviesResponse> = apiService.getMovies(year, apiKey)
}
