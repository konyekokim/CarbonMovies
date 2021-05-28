package com.konyekokim.movies.di

import com.konyekokim.core.di.CoreComponent
import com.konyekokim.core.di.scopes.FeatureScope
import com.konyekokim.movies.MoviesFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [MoviesModule::class],
    dependencies = [CoreComponent::class]
)
interface MoviesComponent {
    fun inject(moviesFragment: MoviesFragment)
}