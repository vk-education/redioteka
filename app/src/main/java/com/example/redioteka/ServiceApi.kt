package com.example.redioteka

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.redioteka.models.User
import com.example.redioteka.models.Actor
import com.example.redioteka.models.Movie

interface ServiceApi {
    @GET("/users/{user}")
    suspend fun getUser(@Path("user") userId: String): User

    @GET("/actors/{actor}")
    suspend fun getActor(@Path("actor") actorId: String): Actor

    @GET("/media/movie/{movie}")
    suspend fun getMovie(@Path("movie") movieId: String): Movie
}