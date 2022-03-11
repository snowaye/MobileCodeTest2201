package com.codigo.mobilecodetest.codivie.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.codigo.mobilecodetest.codivie.adapters.RecommendedMovieAdapter
import com.codigo.mobilecodetest.codivie.adapters.UpcomingMovieAdapter
import com.codigo.mobilecodetest.codivie.databinding.FragmentMoviesBinding
import com.codigo.mobilecodetest.codivie.utils.hide
import com.codigo.mobilecodetest.codivie.utils.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import com.codigo.mobilecodetest.codivie.utils.Coroutines

class MoviesFragment() : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: MoviesViewModelFactory by instance()
    private val viewModel: MoviesViewModel by instance()

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter
    private lateinit var recommendedAdapter: RecommendedMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        context ?: return binding.root
        upcomingMovieAdapter = UpcomingMovieAdapter()
        recommendedAdapter = RecommendedMovieAdapter()
        binding.rvUpcomingMovies.adapter = upcomingMovieAdapter
        binding.rvRecommendedMovies.adapter = recommendedAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUI()
    }

    private fun bindUI()=Coroutines.main {
        binding.layoutData.visibility = GONE
        binding.progressBar.show()
        viewModel.recommendedMovies.value.await().observe(viewLifecycleOwner, Observer {
            recommendedAdapter.submitList(it)
            binding.progressBar.hide()
            binding.layoutData.visibility = VISIBLE
            binding.layoutEmpty.visibility = if(it.isNullOrEmpty()) VISIBLE else GONE
            binding.tvRecommendedMovieTitle.visibility = if(it.isNullOrEmpty()) GONE else VISIBLE
            binding.rvRecommendedMovies.visibility = if(it.isNullOrEmpty()) GONE else VISIBLE
        })
        binding.progressBar.show()
        binding.layoutData.visibility = VISIBLE
        viewModel.upcomingMovies.value.await().observe(viewLifecycleOwner, Observer {
            upcomingMovieAdapter.submitList(it)
            binding.progressBar.hide()
            binding.layoutEmpty.visibility = if(it.isNullOrEmpty()) VISIBLE else GONE
            binding.tvRecommendedMovieTitle.visibility = if(it.isNullOrEmpty()) GONE else VISIBLE
            binding.rvRecommendedMovies.visibility = if(it.isNullOrEmpty()) GONE else VISIBLE
        })

    }

}