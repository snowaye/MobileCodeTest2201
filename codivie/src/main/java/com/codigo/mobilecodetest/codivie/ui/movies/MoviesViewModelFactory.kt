package com.codigo.mobilecodetest.codivie.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codigo.mobilecodetest.codivie.data.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val repository: MoviesRepository):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository) as T
    }
}