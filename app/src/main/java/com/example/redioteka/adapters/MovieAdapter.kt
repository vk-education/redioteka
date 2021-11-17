package com.example.redioteka.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redioteka.databinding.MovieItemBinding
import com.example.redioteka.models.Movie
import com.example.redioteka.views.MoviePageView

class MovieAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(
    MovieModelComparator
) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as? MovieViewHolder)?.bind(item)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MoviePageView::class.java)
            val movieID = (item as Movie).id.toString()
            intent.putExtra(MoviePageView.MOVIE_ID, movieID)
            context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            MovieItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    class MovieViewHolder(val movieItemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(movieItemBinding.root) {
        fun bind(movie: Movie?) {
            movieItemBinding.cardTitle.text = movie?.title
            Glide.with(itemView).load(movie?.avatar).into(movieItemBinding.cardImage)
        }

    }

    interface OnMovieListener {
        fun onMovieClick(position: Int) {
        }
    }

    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

}
