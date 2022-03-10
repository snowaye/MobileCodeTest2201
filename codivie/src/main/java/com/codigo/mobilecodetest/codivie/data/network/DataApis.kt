package com.codigo.mobilecodetest.codivie.data.network

import com.codigo.mobilecodetest.codivie.data.network.response.MoviesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApis {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY ="e5c213a30cee4103f16bee2fbe901f03"

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : DataApis{

            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataApis::class.java)
        }

    }

    @GET("movie/upcoming")
    suspend fun getMovies(
        @Query("api_key") apiKey:String? = API_KEY
    ) : Response<MoviesResponse>

}