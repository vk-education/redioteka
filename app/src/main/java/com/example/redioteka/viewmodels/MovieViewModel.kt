package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.redioteka.models.Movie
import com.example.redioteka.repository.MovieRepo
import com.example.redioteka.repository.paged.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    private val movieRepo: MovieRepo = MovieRepo()
    private val movieId: String = "2"
    val movie = MutableLiveData<Movie>()


    val movies: Flow<PagingData<Movie>> = getMovieListStream()

    private fun getMovieListStream(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(5)) {
            MoviePagingSource(movieRepo)
        }.flow
    }

    init {
        viewModelScope.launch {
            movie.value = movieRepo.getMovie(movieId)
            Log.i("QUERY", movie.value.toString())
        }
    }
}
