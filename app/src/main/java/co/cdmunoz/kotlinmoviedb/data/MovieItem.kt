package co.cdmunoz.kotlinmoviedb.data

import com.squareup.moshi.Json

data class MovieItem(
        @Json(name = "overview")
        val overview: String? = null,
        @Json(name = "original_language")
        val originalLanguage: String? = null,
        @Json(name = "original_title")
        val originalTitle: String? = null,
        @Json(name = "video")
        val video: Boolean? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "genre_ids")
        val genreIds: List<Int?>? = null,
        @Json(name = "poster_path")
        val posterPath: String? = null,
        @Json(name = "backdrop_path")
        val backdropPath: String? = null,
        @Json(name = "release_date")
        val releaseDate: String? = null,
        @Json(name = "vote_average")
        val voteAverage: Double? = null,
        @Json(name = "popularity")
        val popularity: Double? = null,
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "adult")
        val adult: Boolean? = null,
        @Json(name = "vote_count")
        val voteCount: Double? = null
)