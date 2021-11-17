package com.example.redioteka.repository

import com.example.redioteka.ServiceApi
import com.example.redioteka.models.Movie
import com.example.redioteka.models.MovieTop
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://redioteka.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val api = retrofit.create(ServiceApi::class.java)

    suspend fun getMovie(movieId: String): Movie = withContext(Dispatchers.IO) {
        return@withContext api.getMovie(movieId)
    }

    suspend fun getTopMovies(limit: Int, offset: Int): MovieTop = withContext(Dispatchers.IO) {
        return@withContext api.getTop(limit, offset, "movie")
    }
}
