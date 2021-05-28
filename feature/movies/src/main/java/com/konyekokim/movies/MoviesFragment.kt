package com.konyekokim.movies

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.konyekokim.commons.extensions.appContext
import com.konyekokim.commons.extensions.observe
import com.konyekokim.commons.extensions.showSnackbar
import com.konyekokim.commons.ui.GridViewItemDecoration
import com.konyekokim.core.data.DataState
import com.konyekokim.core.data.entities.Movie
import com.konyekokim.core.di.provider.CoreComponentProvider
import com.konyekokim.movies.adapter.MoviesAdapter
import com.konyekokim.movies.adapter.StatusFooterAdapter
import com.konyekokim.movies.databinding.MoviesFragmentMoviesBinding
import com.konyekokim.movies.di.DaggerMoviesComponent
import com.konyekokim.movies.di.MoviesModule
import javax.inject.Inject

class MoviesFragment: Fragment(R.layout.movies_fragment_movies) {

    @Inject
    lateinit var viewModel: MoviesViewModel

    private lateinit var binding: MoviesFragmentMoviesBinding

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var statusFooterAdapter: StatusFooterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MoviesFragmentMoviesBinding.bind(view)
        setUpRecyclerView()
        observe(viewModel.state, ::onViewStateChanged)
        observe(viewModel.data, ::onViewDataChanged)
        observe(viewModel.event, ::onViewEvent)
    }

    private fun setUpRecyclerView(){
        moviesAdapter = MoviesAdapter(viewModel)
        statusFooterAdapter = StatusFooterAdapter(viewModel)
        with(binding.moviesList) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                GridViewItemDecoration(
                    spanCount = resources.getInteger(R.integer.movies_movie_list_span_count),
                    spacing = resources.getDimensionPixelOffset(R.dimen.movies_movie_item_spacing)
                )
            )
            adapter = MergeAdapter(moviesAdapter, statusFooterAdapter)
        }

    }

    private fun onViewStateChanged(viewState: MoviesViewState) {
        statusFooterAdapter.viewState = viewState
        when (val dataState = viewState.dataState) {
            is DataState.Error -> {
                showSnackbar(dataState.message)
            }
            else -> {
                // do nothing
            }
        }
    }

    private fun onViewDataChanged(movies: PagedList<Movie>) {
        moviesAdapter.submitList(movies)
    }

    private fun onViewEvent(viewEvent: MoviesEvent) {
        when (viewEvent) {
            is MoviesEvent.OpenMovieDetail -> {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                        viewEvent.movieId, viewEvent.movieTitle
                    )
                )
            }
        }
    }

    private fun setupDependencyInjection() {
        DaggerMoviesComponent
            .builder()
            .coreComponent((appContext as CoreComponentProvider).provideCoreComponent())
            .moviesModule(MoviesModule(this))
            .build()
            .inject(this)
    }
}