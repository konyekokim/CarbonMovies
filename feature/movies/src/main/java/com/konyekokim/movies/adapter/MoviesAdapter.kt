package com.konyekokim.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.konyekokim.core.data.entities.Movie
import com.konyekokim.movies.MoviesViewModel
import com.konyekokim.movies.databinding.MoviesItemMovieBinding

class MoviesAdapter (
    private val moviesViewModel: MoviesViewModel
    ) : PagedListAdapter<Movie, MoviesAdapter.MovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = MoviesItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MovieItemViewHolder(private val binding: MoviesItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem: Movie) {
            with(binding) {
                movie = movieItem
                viewModel = moviesViewModel
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}