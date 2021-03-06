package com.example.redioteka.models

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("born")
    val birthDate: String,
    @SerializedName("actor_avatar")
    var avatar: String,
    @SerializedName("movies")
    val movies: List<Movie>,
) {
    constructor() : this(1, "firstname", "lastname", "birth", "avatar", listOf())
}

data class SearchResult(
    @SerializedName("movies")
    val movies: List<Movie>,
    @SerializedName("actors")
    val actors: List<Actor>
)

data class Message(
    @SerializedName("message")
    val status: String
)
