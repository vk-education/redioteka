package com.example.redioteka.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.redioteka.R
import com.example.redioteka.databinding.MoviePageBinding
import com.example.redioteka.databinding.SeriesPageBinding
import com.example.redioteka.models.Actor
import com.example.redioteka.viewmodels.MovieViewModel


class SeriesPageView : AppCompatActivity() {

    companion object {
        const val MOVIE_ID: String = "2"
    }

    private val viewModel by viewModels<MovieViewModel>()

    private lateinit var mainBinding: SeriesPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieID = intent?.extras?.getString(MOVIE_ID).toString()

        mainBinding.playButton.setOnClickListener {
            val intent = Intent(it.context, PlayerActivity::class.java)
            intent.putExtra(PlayerActivity.MOVIE_ID, viewModel.movie.value!!.id.toString())
            startActivity(intent)
        }

        mainBinding = SeriesPageBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setMovie(movieID)

    }

    private fun getActorNames(actors: List<Actor>): List<String> {
        val actorList: MutableList<String> = mutableListOf()

        actors.forEach {
            actorList.add(it.firstName + " " + it.lastName)
        }

        return actorList
    }

    @SuppressLint("ResourceAsColor")
    private fun setMovie(id: String) {
        viewModel.loadMovie(id)
        viewModel.movie.observe(this) { movie ->
            Glide.with(this).load(movie?.avatar).into(mainBinding.movieImage)
            mainBinding.movieTitle.text = movie?.title.toString()
            mainBinding.movieYear.text = getString(R.string.movie_year, movie?.year.toString())
            mainBinding.movieRating.text =
                getString(R.string.movie_rating, movie?.rating.toString())
            mainBinding.movieType.text = movie?.type.toString()
            mainBinding.movieDirector.text =
                getString(R.string.movie_director, movie?.directors!!.joinToString(" "))
            mainBinding.movieGenres.text =
                getString(R.string.movie_genres, movie.genres.joinToString(", "))
            mainBinding.movieCountries.text =
                getString(R.string.movie_countries, movie.countries.joinToString(", "))
            mainBinding.movieActors.text =
                getString(
                    R.string.movie_main_actors,
                    getActorNames(movie.actors).joinToString("\n\t")
                )
            mainBinding.movieDescription.text = movie.description

            for ((i, _) in movie.seriesList.withIndex()) {
                val seasonNumber = RadioButton(this)
                seasonNumber.text = (i + 1).toString()
                seasonNumber.setTextColor(resources.getColor(R.color.white))
                mainBinding.seasonNumberSelector.addView(seasonNumber)
                mainBinding.seasonNumberSelector.setOnCheckedChangeListener { _: RadioGroup, number: Int ->
                    mainBinding.serieNumberSelector.removeAllViews()
                    for (j in 0..movie.seriesList[number - 1]) {
                        val serieNumber = RadioButton(this)
                        serieNumber.text = (j + 1).toString()
                        serieNumber.setTextColor(resources.getColor(R.color.white))
                        mainBinding.serieNumberSelector.addView(serieNumber)
                    }
                }
            }

        }
    }
}
