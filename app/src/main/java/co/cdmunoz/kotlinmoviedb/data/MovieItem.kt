package co.cdmunoz.kotlinmoviedb.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "movies")
data class MovieItem(
        @Json(name = "id")
        @PrimaryKey
        val id: Int,
        @Json(name = "overview")
        val overview: String? = null,
        @Json(name = "original_language")
        @ColumnInfo(name = "original_language")
        val originalLanguage: String? = null,
        @Json(name = "original_title")
        @ColumnInfo(name = "original_title")
        val originalTitle: String? = null,
        @Json(name = "video")
        val video: Boolean? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "genre_ids")
        val genreIds: List<Int?>? = null,
        @Json(name = "poster_path")
        @ColumnInfo(name = "poster_path")
        val posterPath: String? = null,
        @Json(name = "backdrop_path")
        @ColumnInfo(name = "backdrop_path")
        val backdropPath: String? = null,
        @Json(name = "release_date")
        @ColumnInfo(name = "release_date")
        val releaseDate: String? = null,
        @Json(name = "vote_average")
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double? = null,
        @Json(name = "popularity")
        val popularity: Double? = null,
        @Json(name = "adult")
        val adult: Boolean? = null,
        @Json(name = "vote_count")
        @ColumnInfo(name = "vote_count")
        val voteCount: Double? = null
): Serializable