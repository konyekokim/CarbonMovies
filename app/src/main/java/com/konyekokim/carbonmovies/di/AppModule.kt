package com.konyekokim.carbonmovies.di

import android.content.Context
import com.konyekokim.carbonmovies.CarbonMoviesApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: CarbonMoviesApplication): Context = application.applicationContext
}