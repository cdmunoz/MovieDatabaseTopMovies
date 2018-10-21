package co.cdmunoz.kotlinmoviedb.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import co.cdmunoz.kotlinmoviedb.data.source.DataConverter

@Database(entities = arrayOf(MovieItem::class), version = 1)
@TypeConverters(DataConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MoviesDatabase? = null
        fun getMoviesDbDatabase(context: Context): MoviesDatabase {
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, MoviesDatabase::class.java,
                        "movies_db.db").build()
            }
            return INSTANCE as MoviesDatabase
        }
    }

    abstract fun moviesDbDao(): MoviesDbDao
}

