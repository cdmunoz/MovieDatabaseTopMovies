package co.cdmunoz.kotlinmoviedb.data

import com.squareup.moshi.Json

data class MoviesResponse(
        @Json(name="page")
        val page: Int? = null,
        @Json(name="total_pages")
        val totalPages: Int? = null,
        @Json(name="results")
        val results: List<MovieItem>? = null,
        @Json(name="total_results")
        val totalResults: Int? = null
)