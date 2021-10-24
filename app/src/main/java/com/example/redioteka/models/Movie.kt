package com.example.redioteka.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("movie_avatar")
    val avatar: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val year: String,
    @SerializedName("id")
    val rating: Float,
    @SerializedName("id")
    val genres: List<String>,
    @SerializedName("id")
    val countries: List<String>,
    @SerializedName("id")
    val director: List<String>,
    @SerializedName("id")
    val actors: List<Actor>,
    @SerializedName("id")
    val isFree: Boolean,
    @SerializedName("id")
    val type: String,
    @SerializedName("id")
    val seriesList: List<Int>
)