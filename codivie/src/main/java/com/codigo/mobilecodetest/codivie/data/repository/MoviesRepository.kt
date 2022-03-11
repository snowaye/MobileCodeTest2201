package com.codigo.mobilecodetest.codivie.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codigo.mobilecodetest.codivie.data.db.AppDatabase
import com.codigo.mobilecodetest.codivie.data.db.entities.Movie
import com.codigo.mobilecodetest.codivie.data.network.BaseRepository
import com.codigo.mobilecodetest.codivie.data.network.DataApis
import com.codigo.mobilecodetest.codivie.utils.Coroutines
import com.codigo.mobilecodetest.codivie.utils.PrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val MINIMUM_INTERVAL = 24L


class MoviesRepository(
    private val api: DataApis,
    private val db: AppDatabase,
    private val pref: PrefManager): BaseRepository()
{
    private val movies = MutableLiveData<List<Movie>>()
    private val recommendedMovies = MutableLiveData<List<Movie>>()

    init {
        movies.observeForever {
            saveMovies(it)
        }
    }

    fun getMovie(movieId: String) = db.movieDao().getMovie(movieId)

    suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            fetchUpcomingMovies()
            db.movieDao().getMovies()
        }
    }

    suspend fun getRecommendedMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            fetchRecommendedMovies()
            db.movieDao().getMovies()
        }
    }


    private suspend fun fetchUpcomingMovies() {
        val lastSavedAt = pref.getLastFetchedTime()

        if (lastSavedAt <=0 || isFetchNeeded(lastSavedAt)) {
            try {
                val response = apiRequest { api.getUpcomingMovies() }
                movies.postValue(response.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun fetchRecommendedMovies() {
        val lastSavedAt = pref.getLastFetchedTime()

        if (lastSavedAt <=0 || isFetchNeeded(lastSavedAt)) {
            try {
                val response = apiRequest { api.getRecommendedMovies() }
                movies.postValue(response.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(lastSavedTime: Long): Boolean {
        val duration = System.currentTimeMillis() - lastSavedTime
        return duration>= MINIMUM_INTERVAL
    }


    private fun saveMovies(movies: List<Movie>) {
        Coroutines.io {
            pref.setLastFetchedTime(System.currentTimeMillis())
            db.movieDao().insertAll(movies)
        }
    }


}

