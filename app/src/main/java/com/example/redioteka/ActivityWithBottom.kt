package com.example.redioteka

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel
import com.example.redioteka.views.MoviesViewFragment
import com.example.redioteka.views.SeriesViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ActivityWithBottom : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()
    private val actorViewModel by viewModels<ActorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_with_bottom)

        if (savedInstanceState == null) {
            replaceFragment(MoviesViewFragment())
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_movies -> replaceFragment(MoviesViewFragment())
                R.id.ic_series -> replaceFragment(SeriesViewFragment())
            }
            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).itemIconTintList = null
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
