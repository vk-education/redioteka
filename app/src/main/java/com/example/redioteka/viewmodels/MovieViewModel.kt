package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.Movie
import com.example.redioteka.repository.MovieRepo
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    private val movieRepo: MovieRepo = MovieRepo()
    private val movieId: String = "2"
    val movie = MutableLiveData<Movie>()

    init {
        viewModelScope.launch {
            movie.value = movieRepo.getMovie(movieId)
            Log.i("QUERY", movie.value.toString())
        }
    }
}