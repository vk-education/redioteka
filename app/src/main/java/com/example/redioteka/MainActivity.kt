package com.example.redioteka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.redioteka.repository.ActorRepo
import com.example.redioteka.repository.MovieRepo
import com.example.redioteka.repository.UserRepo
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel


class MainActivity : AppCompatActivity() {
    private val userRepo: UserRepo = UserRepo()
    private val userViewModel: UserViewModel = UserViewModel(userRepo, "1")
    private val movieRepo: MovieRepo = MovieRepo()
    private val movieViewModel: MovieViewModel = MovieViewModel(movieRepo, "2")
    private val actorRepo: ActorRepo = ActorRepo()
    private val actorViewModel: ActorViewModel = ActorViewModel(actorRepo, "3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}