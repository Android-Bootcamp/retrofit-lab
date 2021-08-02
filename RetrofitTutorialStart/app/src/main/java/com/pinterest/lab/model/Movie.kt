package com.pinterest.lab.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("vote_average") val voteAverage: Float,
    @field:SerializedName("poster_path") val posterPath: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("overview") val overview: String
)