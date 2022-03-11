package com.codigo.mobilecodetest.codivie.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie (
    @SerializedName("id")
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    var release_date: String
) {
    override fun toString() = title

    fun toRatingK(v: Int): String {
        val kValue = v/1000
        return StringBuilder().append(String.format("%2d ", kValue)).append("K").toString()

    }

    fun toPercentage(v: Double): String {
        val percentage = v * 10
        return StringBuilder().append(String.format("%2d ", percentage.toInt())).append("%").toString()
    }

    fun toVoteCount(v: Int): String {
        return StringBuilder().append(this).append(" votes").toString()
    }
}

