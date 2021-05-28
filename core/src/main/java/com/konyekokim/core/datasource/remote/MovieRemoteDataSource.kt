package com.konyekokim.core.datasource.remote

import com.konyekokim.core.data.Result
import com.konyekokim.core.network.responses.PopularMoviesResponse
import com.konyekokim.core.network.responses.PopularMoviesResponseItem
import com.konyekokim.core.network.services.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
){

    suspend fun fetchMovies(page: Int): Result<List<PopularMoviesResponseItem>> {
        return try {
            val response = movieService.fetchMovies(page)
            getResult(response = response, onError = {
                Result.Error("Error Fetching movies ${response.code()} ${response.message()}")
            })
        } catch (e: Exception){
            Result.Error("Error fetching movies")
        }
    }

    private inline fun getResult(
        response: Response<PopularMoviesResponse>,
        onError: () -> Result.Error
    ): Result<List<PopularMoviesResponseItem>> {
        if(response.isSuccessful){
            val body = response.body()
            if(body != null) {
                return Result.Success(body.results)
            }
        }
        return onError.invoke()
    }
}