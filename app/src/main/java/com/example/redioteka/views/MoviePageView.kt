package com.example.redioteka.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.redioteka.databinding.MoviePageBinding
import com.example.redioteka.models.Movie
import com.example.redioteka.viewmodels.MovieViewModel
import com.bumptech.glide.Glide


class MoviePageView : AppCompatActivity() {
    private val viewModel by viewModels<MovieViewModel>()

    private lateinit var mainBinding: MoviePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MoviePageBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setMovie()
    }

    private fun setMovie() {
        val movie: Movie? = viewModel.getMovie("2")

//        Glide.with(this).load(movie?.avatar).into(mainBinding.movieImage)
        mainBinding.movieTitle.text = movie?.title.toString()
        mainBinding.movieRating.text = movie?.rating.toString()
        mainBinding.movieType.text = movie?.type.toString()
        mainBinding.movieYear.text = movie?.year.toString()
        mainBinding.movieDirector.text = movie?.directors.toString()
        mainBinding.movieGenres.text = movie?.genres.toString()
        mainBinding.movieCountries.text = movie?.countries.toString()
        mainBinding.movieActors.text = movie?.actors.toString()
        mainBinding.movieDescription.text = movie?.description.toString()
    }
}