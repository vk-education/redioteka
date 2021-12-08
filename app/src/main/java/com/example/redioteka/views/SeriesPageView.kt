package com.example.redioteka.views
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.redioteka.databinding.MoviePageBinding
import com.example.redioteka.models.Actor
import com.example.redioteka.viewmodels.MovieViewModel

class SeriesPageView : AppCompatActivity() {

    companion object {
        const val MOVIE_ID: String = "91"
    }

    private val viewModel by viewModels<MovieViewModel>()

    private lateinit var mainBinding: MoviePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val letterId = intent?.extras?.getString(MOVIE_ID).toString()

        mainBinding = MoviePageBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setMovie(letterId)

    }
    /*
    Tablayout
     */

    private fun getActorNames(actors: List<Actor>): List<String> {
        val actorList: MutableList<String> = mutableListOf()

        actors.forEach {
            actorList.add(it.firstName + " " + it.lastName)
        }

        return actorList
    }

    private fun setMovie(id: String) {
        viewModel.loadMovie(id)
        viewModel.movie.observe(this) { movie ->
            Glide.with(this).load(movie?.avatar).into(mainBinding.movieImage)
            mainBinding.movieTitle.text = movie?.title.toString()
            mainBinding.movieYear.text = "ГОД: ${movie?.year.toString()}"
            mainBinding.movieRating.text = "РЕЙТИНГ ${movie?.rating.toString()}/10"
            mainBinding.movieType.text = movie?.type.toString()
            mainBinding.movieDirector.text = "РЕЖИССЕР: ${movie?.directors!!.joinToString(" ")}"
            mainBinding.movieGenres.text = "ЖАНРЫ: ${movie?.genres!!.joinToString(", ")}"
            mainBinding.movieCountries.text = "СТРАНЫ: ${movie?.countries!!.joinToString(", ")}"
            mainBinding.movieActors.text =
                "В главных ролях:\n\t${getActorNames(movie?.actors!!).joinToString("\n\t")}"
            mainBinding.movieDescription.text = movie.description.toString()
        }
    }
}