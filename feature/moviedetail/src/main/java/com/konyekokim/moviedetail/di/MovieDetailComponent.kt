package com.konyekokim.moviedetail.di

import com.konyekokim.core.di.CoreComponent
import com.konyekokim.core.di.scopes.FeatureScope
import com.konyekokim.moviedetail.MovieDetailFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [MovieDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface MovieDetailComponent {
    fun inject(movieDetailFragment: MovieDetailFragment)
}