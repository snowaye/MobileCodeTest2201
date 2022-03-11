/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codigo.mobilecodetest.codivie.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.mobilecodetest.codivie.R
import com.codigo.mobilecodetest.codivie.data.db.entities.Movie
import com.codigo.mobilecodetest.codivie.databinding.ItemRecommendedMoviesBinding
import com.codigo.mobilecodetest.codivie.ui.home.HomeFragmentDirections

/**
 * Adapter for the [RecyclerView] in [MoviesFragment].
 */
class RecommendedMovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecommendedMovieViewHolder(
            ItemRecommendedMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as RecommendedMovieViewHolder).bind(movie)
    }

    class RecommendedMovieViewHolder(
        private val binding: ItemRecommendedMoviesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Log.i("MVIe", "seOnclickListerner")
                binding.movie?.let { movie ->
                    navigateToMovieDetail(movie, it)
                }
            }
        }

        private fun navigateToMovieDetail(
            movie: Movie,
            view: View
        ) {
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            val bundle = bundleOf("movieId" to movie.id)
            val navController by lazy { view.findNavController() }
            navController.setGraph(R.navigation.nav_graph, bundle)
            navController.navigate(direction)
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}


