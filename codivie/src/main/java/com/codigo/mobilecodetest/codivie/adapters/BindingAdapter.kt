package com.codigo.mobilecodetest.codivie.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.codigo.mobilecodetest.codivie.R
import com.codigo.mobilecodetest.codivie.ui.home.HomeFragmentDirections

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, poster_path: String?) {
    if (!poster_path.isNullOrEmpty()) {
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/w92"+poster_path)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.movie_avatar)
            .error(R.drawable.movie_avatar)
            .into(view)
    }
}
