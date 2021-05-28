package com.konyekokim.movies.di

import androidx.lifecycle.viewModelScope
import com.konyekokim.commons.extensions.viewModel
import com.konyekokim.core.di.scopes.FeatureScope
import com.konyekokim.core.repositories.MovieRepository
import com.konyekokim.movies.MoviesFragment
import com.konyekokim.movies.MoviesPageDataSource
import com.konyekokim.movies.MoviesPageDataSourceFactory
import com.konyekokim.movies.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module
class MoviesModule(
    private val moviesFragment: MoviesFragment
) {

    @Provides
    @FeatureScope
    fun provideViewModel(
        pageDataSourceFactory: MoviesPageDataSourceFactory
    ) = moviesFragment.viewModel {
        MoviesViewModel(pageDataSourceFactory)
    }

    @Provides
    fun provideMovieDataSource(
        moviesRepository: MovieRepository,
        viewModel: MoviesViewModel
    ) = MoviesPageDataSource(moviesRepository, viewModel.viewModelScope)

}