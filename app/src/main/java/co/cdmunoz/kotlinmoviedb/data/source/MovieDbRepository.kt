package co.cdmunoz.kotlinmoviedb.data.source

import android.os.AsyncTask
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import co.cdmunoz.kotlinmoviedb.data.source.local.MoviesDbDao
import co.cdmunoz.kotlinmoviedb.data.source.remote.MovieDbApiService


class MovieDbRepository constructor(val apiService: MovieDbApiService, val moviesDbDao: MoviesDbDao) {

    fun getMoviesFromApi(year: String, apiKey: String) = apiService.getMovies(year, apiKey)

    fun getMoviesFromDb() = moviesDbDao.queryMovies()

    fun insertMovieIntoDb(movieItem: MovieItem) = InsertMovieItemAsyncTask(moviesDbDao).execute(movieItem)

    private class InsertMovieItemAsyncTask constructor(private val moviesDbDao: MoviesDbDao) : AsyncTask<MovieItem, Void, Void>() {
        override fun doInBackground(vararg params: MovieItem): Void? {
            moviesDbDao.insertMovie(params[0])
            return null
        }
    }
}