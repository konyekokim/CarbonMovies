package com.konyekokim.moviedetail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.konyekokim.commons.extensions.*
import com.konyekokim.core.data.DataState
import com.konyekokim.core.data.entities.Movie
import com.konyekokim.core.di.provider.CoreComponentProvider
import com.konyekokim.moviedetail.databinding.MoviedetailFragmentDetailBinding
import com.konyekokim.moviedetail.di.DaggerMovieDetailComponent
import com.konyekokim.moviedetail.di.MovieDetailModule
import javax.inject.Inject

class MovieDetailFragment : Fragment(R.layout.moviedetail_fragment_detail){

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    private lateinit var binding: MoviedetailFragmentDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setUpDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MoviedetailFragmentDetailBinding.bind(view)

        observe(viewModel.state, ::onViewStateChanged)
        observe(viewModel.data, ::onViewDataChanged)

        viewModel.fetchMovie(args.movieId)
    }

    private fun onViewStateChanged(viewState: MovieDetailViewState) {
        when (val dataState = viewState.dataState) {
            DataState.Success -> binding.progress.hide()
            DataState.Loading -> binding.progress.show()
            is DataState.Error -> {
                binding.progress.hide()
                showSnackbar(dataState.message)
            }
        }
    }

    private fun onViewDataChanged(movie: Movie) {
        binding.title.text = movie.title
        binding.overview.text = movie.overview
        binding.poster.loadImage(movie.posterUrl)
    }

    private fun setUpDependencyInjection(){
        DaggerMovieDetailComponent
            .builder()
            .coreComponent((appContext as CoreComponentProvider).provideCoreComponent())
            .movieDetailModule(MovieDetailModule(this))
            .build()
            .inject(this)
    }

}