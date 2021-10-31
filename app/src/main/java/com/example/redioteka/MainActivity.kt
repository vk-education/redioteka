package com.example.redioteka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel


class MainActivity : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()
    private val actorViewModel by viewModels<ActorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uvm = userViewModel
        var mvm = movieViewModel
        var avm = actorViewModel
    }
}
