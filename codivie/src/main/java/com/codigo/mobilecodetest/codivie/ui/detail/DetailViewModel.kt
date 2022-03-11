package com.codigo.mobilecodetest.codivie.ui.detail

import androidx.lifecycle.ViewModel
import com.codigo.mobilecodetest.codivie.data.repository.MoviesRepository

class DetailViewModel(private val moviesRepository: MoviesRepository,
private val movieId: String) : ViewModel() {

    val movie = moviesRepository.getMovie(movieId)


}
