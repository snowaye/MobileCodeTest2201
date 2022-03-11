package com.codigo.mobilecodetest.codivie.ui.movies


import androidx.lifecycle.ViewModel
import com.codigo.mobilecodetest.codivie.data.repository.MoviesRepository
import com.codigo.mobilecodetest.codivie.utils.lazyDeferred

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    val upcomingMovies = lazyDeferred {
        repository.getUpcomingMovies()
    }

    val recommendedMovies = lazyDeferred {
        repository.getRecommendedMovies()
    }
}