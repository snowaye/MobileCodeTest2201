package com.codigo.mobilecodetest.codivie.data.network.response

import com.codigo.mobilecodetest.codivie.data.db.entities.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("results")
    val results: List<Movie>
)



