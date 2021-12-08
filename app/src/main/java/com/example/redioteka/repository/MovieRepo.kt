package com.example.redioteka.repository

import android.content.Context
import com.example.redioteka.models.Movie
import com.example.redioteka.models.MovieTop
import com.example.redioteka.models.Stream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo(context: Context) {
    private val api = createApi(context)

    suspend fun getMovie(movieId: String): Movie = withContext(Dispatchers.IO) {
        return@withContext api.getMovie(movieId)
    }

    suspend fun getTopMovies(limit: Int, offset: Int): MovieTop = withContext(Dispatchers.IO) {
        return@withContext api.getTop(limit, offset, "movie")
    }

    suspend fun getMovieStream(id: String): List<Stream> = withContext(Dispatchers.IO) {
        return@withContext api.getMovieStream(id)
    }

    suspend fun getTopSeries(limit: Int, offset: Int): MovieTop = withContext(Dispatchers.IO) {
        return@withContext api.getTop(limit, offset, "series")
    }
}
