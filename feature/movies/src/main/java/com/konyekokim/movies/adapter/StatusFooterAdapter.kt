package com.konyekokim.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konyekokim.movies.MoviesViewModel
import com.konyekokim.movies.MoviesViewState
import com.konyekokim.movies.databinding.MoviesItemLoadStatusBinding

class StatusFooterAdapter (private val moviesViewModel: MoviesViewModel) :
    RecyclerView.Adapter<StatusFooterAdapter.StatusFooterViewHolder>(){

    internal var viewState: MoviesViewState? = null
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusFooterViewHolder {
        val binding = MoviesItemLoadStatusBinding.inflate(LayoutInflater.from(parent.context))
        return StatusFooterViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: StatusFooterViewHolder, position: Int) {
        holder.bind(viewState, moviesViewModel)
    }

    inner class StatusFooterViewHolder(private val binding: MoviesItemLoadStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(state: MoviesViewState?, moviesViewModel: MoviesViewModel) {
            state?.let {
                with(binding) {
                    viewState = state
                    viewModel = moviesViewModel
                    executePendingBindings()
                }
            }
        }
    }

}