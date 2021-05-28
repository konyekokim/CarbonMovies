package com.konyekokim.core.datasource.local

import com.konyekokim.core.data.entities.Movie
import com.konyekokim.core.database.MovieDao
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun insertAll (movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

    suspend fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)

}