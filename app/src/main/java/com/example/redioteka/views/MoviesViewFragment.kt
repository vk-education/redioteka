package com.example.redioteka.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.redioteka.adapters.MovieAdapter
import com.example.redioteka.adapters.MovieLoadStateAdapter
import com.example.redioteka.databinding.LoginFragmentBinding
import com.example.redioteka.databinding.MovieViewBinding
import com.example.redioteka.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesViewFragment: Fragment() {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    protected var _binding: MovieViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieViewBinding.inflate(inflater)
        return binding().root
    }

    protected fun binding() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()


        binding().movieRecycler.apply {
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

        binding().btnRetry.setOnClickListener {
            movieAdapter.retry()
        }

        // show the loading state for te first load
        movieAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                binding().btnRetry.visibility = View.GONE

                // Show ProgressBar
                binding().progressBar.visibility = View.VISIBLE
            } else {
                // Hide ProgressBar
                binding().progressBar.visibility = View.GONE

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        binding().btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}