package com.konyekokim.moviedetail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konyekokim.core.data.DataState
import com.konyekokim.core.data.entities.Movie
import com.konyekokim.core.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _data = MutableLiveData<Movie>()
    val data: LiveData<Movie>
        get() = _data

    private val _state = MutableLiveData<MovieDetailViewState>()
    val state: LiveData<MovieDetailViewState>
        get() = _state

    fun fetchMovie(movieId: Int) {
        _state.postValue(MovieDetailViewState(dataState = DataState.Loading))
        viewModelScope.launch {
            val movie = movieRepository.getMovieById(movieId)
            if (movie != null) {
                _state.postValue(MovieDetailViewState(dataState = DataState.Success))
                _data.postValue(movie)
            } else {
                _state.postValue(
                    MovieDetailViewState(
                        dataState = DataState.Error(ERROR_MESSAGE)
                    )
                )
            }
        }
    }

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val ERROR_MESSAGE = "Error occurred during fetching the movie"
    }
}