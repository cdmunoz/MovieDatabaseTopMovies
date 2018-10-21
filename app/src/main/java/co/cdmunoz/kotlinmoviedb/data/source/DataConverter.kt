package co.cdmunoz.kotlinmoviedb.data.source

import android.arch.persistence.room.TypeConverter

class DataConverter {

    @TypeConverter
    fun fromListToGenres(genres: List<Int>?): String {
        if (genres == null) return ""
        return genres.joinToString(",")
    }
    @TypeConverter
    fun fromStringToList(genres: String?): List<Int> {
        if (genres == null) return ArrayList()
        return genres.split(",").map { it.toInt() }
    }
}