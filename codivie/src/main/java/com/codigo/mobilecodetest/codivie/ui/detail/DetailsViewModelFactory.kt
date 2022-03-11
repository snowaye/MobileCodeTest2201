package com.codigo.mobilecodetest.codivie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codigo.mobilecodetest.codivie.data.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repository: MoviesRepository, private val movieId: String):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository, movieId) as T
    }
}