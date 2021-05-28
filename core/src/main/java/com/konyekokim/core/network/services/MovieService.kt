package com.konyekokim.core.network.services

import com.konyekokim.core.network.responses.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchMovies(@Query("page") page: Int): Response<PopularMoviesResponse>
}