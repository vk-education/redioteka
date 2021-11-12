package com.example.redioteka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.redioteka.repository.ActorRepo
import com.example.redioteka.repository.MovieRepo
import com.example.redioteka.repository.UserRepo
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel
import com.example.redioteka.views.MovieView


class MainActivity : AppCompatActivity() {
    private val userRepo: UserRepo = UserRepo()
    private val userViewModel: UserViewModel = UserViewModel(userRepo, "1")
    private val movieRepo: MovieRepo = MovieRepo()
    private val actorRepo: ActorRepo = ActorRepo()
    private val actorViewModel: ActorViewModel = ActorViewModel(actorRepo, "3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MovieView::class.java)
        startActivity(intent)

    }
}