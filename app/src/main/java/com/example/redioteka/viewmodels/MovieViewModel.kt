package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.Movie
import com.example.redioteka.repository.MovieRepo
import kotlinx.coroutines.launch

class MovieViewModel(movieRepo: MovieRepo, movieId: String) : ViewModel() {
    private var movie: Movie = Movie()

    init {
        viewModelScope.launch {
            movie = movieRepo.getMovie(movieId)
            Log.i("QUERY", movie.toString())
        }
    }
}