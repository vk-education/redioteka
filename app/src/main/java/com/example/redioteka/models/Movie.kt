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
    val rating: Double,
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
) {
    constructor() : this(1, "title", "avatar", "descr", "year", 5.5,
        listOf("genre"), listOf("countries"), listOf("directors"), listOf(), false, "type", listOf(), 0)
}

data class MovieFavourite(
    @SerializedName("favourites")
    val movies: List<Movie>
)

data class MovieTop(
    @SerializedName("top")
    val movies: List<Movie>
)

data class MovieNewest(
    @SerializedName("newest")
    val movies: List<Movie>
)

data class Genre(
    @SerializedName("name")
    val name: String,
    @SerializedName("label_rus")
    val label: String,
    @SerializedName("image")
    val image: String
)

data class MovieGenre(
    @SerializedName("genre")
    val movies: List<Movie>
)

data class Stream(
    @SerializedName("video_path")
    val video: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("series")
    val series: String
)