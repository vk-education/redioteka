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
    @SerializedName("year")
    val year: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("countries")
    val countries: List<String>,
    @SerializedName("director")
    val directors: List<String>,
    @SerializedName("actors")
    val actors: List<Actor>,
    @SerializedName("is_free")
    val isFree: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("series_list")
    val seriesList: List<Int>,
    @SerializedName("availability")
    val available: Int,
)