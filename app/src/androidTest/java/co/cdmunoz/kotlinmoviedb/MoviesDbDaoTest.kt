package co.cdmunoz.kotlinmoviedb

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import co.cdmunoz.kotlinmoviedb.data.source.local.MoviesDatabase
import co.cdmunoz.kotlinmoviedb.data.source.local.MoviesDbDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class MoviesDbDaoTest {
    private var moviesDatabase: MoviesDatabase? = null
    private var moviesDbDao: MoviesDbDao? = null
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        //use in memory db. The data will be deleted after the process is killed
        moviesDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MoviesDatabase::class.java)
                .allowMainThreadQueries()//only for testing purposes
                .build()
        moviesDbDao = moviesDatabase!!.moviesDbDao()
    }

    @After
    fun closeDb() {
        moviesDatabase!!.close()
    }

    @Test
    fun getMoviesWhenNoMoviesInserted() {
        val movies = getValue(moviesDbDao!!.queryMovies())
        assertEquals(0, movies.size)
    }

    @Test
    fun insertMovie_SuccessfullyInsertMovie() {
        val movieItem = populateMovieItemWithData()
        moviesDbDao!!.insertMovie(movieItem)
        val movies = getValue(moviesDbDao!!.queryMovies())
        assertEquals(1, movies.size)
        assertEquals(movies[0].id, movieItem.id)
        moviesDbDao!!.deleteAllMovies()//to allow running new tests with the initial conditions
    }

    private fun populateMovieItemWithData(): MovieItem {
        val genreIds = ArrayList<Int>()
        genreIds.add(1)
        genreIds.add(2)
        genreIds.add(3)
        return MovieItem(1, "Overview", "EN", "Title", false, "Title",
                genreIds, "path_poster", "back_path", "2018-09-21", 8.0,
                123.0, false, 8.0)
    }

    companion object {
        /**
         * This is used to make sure the method waits till data is available from the observer.
         */
        @Throws(InterruptedException::class)
        fun <T> getValue(liveData: LiveData<T>): T {
            val data = arrayOfNulls<Any>(1)
            val latch = CountDownLatch(1)
            val observer = object : Observer<T> {
                override fun onChanged(o: T?) {
                    data[0] = o
                    latch.countDown()
                    liveData.removeObserver(this)
                }
            }
            liveData.observeForever(observer)
            latch.await(2, TimeUnit.SECONDS)

            return data[0] as T
        }
    }
}