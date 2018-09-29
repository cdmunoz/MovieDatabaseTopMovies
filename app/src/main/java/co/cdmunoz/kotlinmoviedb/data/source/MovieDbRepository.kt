package co.cdmunoz.kotlinmoviedb.data.source

import co.cdmunoz.kotlinmoviedb.data.source.remote.MovieDbApiService

class MovieDbRepository constructor(val apiService: MovieDbApiService) {

    fun getMovies(year: String, apiKey: String) = getMoviesFromApi(year, apiKey)

    private fun getMoviesFromApi(year: String, apiKey: String) = apiService.getMovies(year, apiKey)
}