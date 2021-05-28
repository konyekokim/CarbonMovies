package com.konyekokim.moviedetail.di

import com.konyekokim.commons.extensions.viewModel
import com.konyekokim.core.di.scopes.FeatureScope
import com.konyekokim.core.repositories.MovieRepository
import com.konyekokim.moviedetail.MovieDetailFragment
import com.konyekokim.moviedetail.MovieDetailViewModel
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule(private val movieDetailFragment: MovieDetailFragment) {

    @Provides
    @FeatureScope
    fun provideViewModel(
        movieRepository: MovieRepository
    ) = movieDetailFragment.viewModel {
        MovieDetailViewModel(movieRepository)
    }

}