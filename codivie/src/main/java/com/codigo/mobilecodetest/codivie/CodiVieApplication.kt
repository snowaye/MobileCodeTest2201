package com.codigo.mobilecodetest.codivie

import android.app.Application
import com.codigo.mobilecodetest.codivie.data.db.AppDatabase
import com.codigo.mobilecodetest.codivie.data.network.DataApis
import com.codigo.mobilecodetest.codivie.data.network.NetworkConnectionInterceptor
import com.codigo.mobilecodetest.codivie.data.repository.MoviesRepository
import com.codigo.mobilecodetest.codivie.ui.movies.MoviesViewModel
import com.codigo.mobilecodetest.codivie.ui.movies.MoviesViewModelFactory
import com.codigo.mobilecodetest.codivie.utils.PrefManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class CodiVieApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@CodiVieApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { DataApis(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PrefManager(instance()) }
        bind() from singleton { MoviesRepository(instance(), instance(), instance()) }
        bind() from provider { MoviesViewModelFactory(instance()) }
        bind() from provider { MoviesViewModel(instance()) }
//        bind() from factory {
//                movieId: String ->
//            DetailsViewModelFactory(instance(), movieId)
//        }
//        bind() from provider { DetailViewModel(instance(), instance()) }
    }

}