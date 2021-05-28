package com.konyekokim.carbonmovies.di

import com.konyekokim.carbonmovies.CarbonMoviesApplication
import com.konyekokim.core.di.CoreComponent
import com.konyekokim.core.di.scopes.AppScope
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: CarbonMoviesApplication)
}