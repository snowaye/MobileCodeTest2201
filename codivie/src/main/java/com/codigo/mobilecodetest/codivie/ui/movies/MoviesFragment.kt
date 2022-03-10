package com.codigo.mobilecodetest.codivie.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.codigo.mobilecodetest.codivie.adapters.MovieAdapter
import com.codigo.mobilecodetest.codivie.databinding.FragmentMoviesBinding
import com.codigo.mobilecodetest.codivie.utils.hide
import com.codigo.mobilecodetest.codivie.utils.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import com.codigo.mobilecodetest.codivie.utils.Coroutines
import org.kodein.di.Kodein
import androidx.lifecycle.get

class MoviesFragment() : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: MoviesViewModelFactory by instance()
    private val viewModel: MoviesViewModel by instance()

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        context ?: return binding.root
        adapter = MovieAdapter()
        binding.movieList.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUI()
    }

    private fun bindUI()=Coroutines.main {
        binding.progressBar.show()
        viewModel.movies.value.await().observe(viewLifecycleOwner, Observer {
            binding.progressBar.hide()
            binding.layoutEmpty.visibility = if(it.isNullOrEmpty()) VISIBLE else GONE
            adapter.submitList(it)
        })
    }


}