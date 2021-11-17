package com.example.redioteka.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.redioteka.adapters.MovieAdapter
import com.example.redioteka.adapters.MovieLoadStateAdapter
import com.example.redioteka.databinding.MovieViewBinding
import com.example.redioteka.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieView : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var mainBinding: MovieViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MovieViewBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        movieAdapter = MovieAdapter()

        mainBinding.movieRecycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }

        mainBinding.btnRetry.setOnClickListener {
            movieAdapter.retry()
        }

        // show the loading state for te first load
        movieAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                mainBinding.btnRetry.visibility = View.GONE

                // Show ProgressBar
                mainBinding.progressBar.visibility = View.VISIBLE
            } else {
                // Hide ProgressBar
                mainBinding.progressBar.visibility = View.GONE

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        mainBinding.btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
