package co.cdmunoz.kotlinmoviedb.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.cdmunoz.kotlinmoviedb.data.MovieItem

@Dao
interface MoviesDbDao {
    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    fun queryMovies(): LiveData<MovieItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieItem: MovieItem)
}