package co.cdmunoz.kotlinmoviedb

import android.app.Application

class MoviesDbApplication : Application() {
    companion object {
        lateinit var instance: MoviesDbApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}