package com.example.redioteka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.redioteka.viewmodels.ActorViewModel
import com.example.redioteka.viewmodels.MovieViewModel
import com.example.redioteka.viewmodels.UserViewModel
import com.example.redioteka.views.MoviePageView


class MainActivity : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()
    private val actorViewModel by viewModels<ActorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        var uvm = userViewModel
        val mvm = movieViewModel
        Log.i("OSDJFOISDJFOISDJFOIJ", mvm.movie.value?.title.toString())
//        var avm = actorViewModel
//

//        val intent = Intent(this, MoviePageView::class.java)
//        startActivity(intent)
    }
}
