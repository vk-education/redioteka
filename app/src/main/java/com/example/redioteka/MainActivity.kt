package com.example.redioteka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel

import com.example.redioteka.views.MoviePageView

import com.example.redioteka.views.LoginFragment


class MainActivity : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()
    private val actorViewModel by viewModels<ActorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        <<<<<<< HEAD
        val intent = Intent(this, MoviePageView::class.java)
        startActivity(intent)
//
//        val intent = Intent(this, MovieView::class.java)
//        startActivity(intent)
//
        ====== =
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment(), "TAG")
                .commit()
        }
        >>>>>>> develop
    }
}
