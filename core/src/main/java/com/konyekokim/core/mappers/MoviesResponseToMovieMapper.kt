package com.konyekokim.core.mappers

import com.konyekokim.core.data.entities.Movie
import com.konyekokim.core.network.responses.PopularMoviesResponseItem
import javax.inject.Inject

class MoviesResponseToMovieMapper @Inject constructor() :
    Mapper<List<PopularMoviesResponseItem>, List<Movie>>{

    override suspend fun map(from: List<PopularMoviesResponseItem>): List<Movie> {
        return from.map {
            Movie(
                id = it.id,
                posterUrl = it.posterUrl,
                overview = it.overview,
                title = it.title
            )
        }
    }
}