package com.example.redioteka.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.redioteka.models.Movie
import com.example.redioteka.repository.MovieRepo
import com.example.redioteka.repository.paged.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {
    private var movie: Movie = Movie()
    private val movieRepo = MovieRepo()

    val movies: Flow<PagingData<Movie>> = getMovieListStream()

    private fun getMovieListStream(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(5)) {
            MoviePagingSource(movieRepo)
        }.flow
    }

//    init {
//        viewModelScope.launch {
//            movie = movieRepo.getMovie(movieId)
//            Log.i("QUERY", movie.toString())
//        }
//    }
}

//class MainViewModel(private val repository: MovieRepo) : ViewModel() {
//
//    val movies: Flow<PagingData<Movie>> = getMovieListStream()
//        .map { pagingData -> pagingData.map { MovieModel.MovieItem(it) } }
//
//
//    private fun getMovieListStream(): Flow<PagingData<Movie>> {
//        return Pager(PagingConfig(20)) {
//            MoviePagingSource(repository)
//        }.flow
//    }
//}