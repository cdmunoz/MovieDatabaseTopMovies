package co.cdmunoz.kotlinmoviedb.data.source.remote

import co.cdmunoz.kotlinmoviedb.BuildConfig
import co.cdmunoz.kotlinmoviedb.data.MoviesResponse
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApiService {
    @GET("discover/movie")
    fun getMovies(@Query("year") movieYear: String, @Query("api_key") api_key: String): Call<MoviesResponse>

    companion object Factory {
        fun create(): MovieDbApiService {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
            return retrofit.create(MovieDbApiService::class.java)
        }
    }
}