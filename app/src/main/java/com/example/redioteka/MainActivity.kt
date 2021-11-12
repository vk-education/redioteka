package com.example.redioteka

import androidx.activity.viewModels
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.redioteka.views.MovieView


class MainActivity : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()
    private val actorViewModel by viewModels<ActorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MovieView::class.java)
        startActivity(intent)

        var uvm = userViewModel
        var mvm = movieViewModel
        var avm = actorViewModel
    }
}