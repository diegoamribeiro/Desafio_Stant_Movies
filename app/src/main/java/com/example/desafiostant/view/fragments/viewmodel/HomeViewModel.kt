package com.example.desafiostant.view.fragments.viewmodel

import androidx.lifecycle.*
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.data.repository.MovieRepository
import kotlinx.coroutines.launch
import com.example.desafiostant.utils.Resource
import retrofit2.Response

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
    val nowPlaying: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var currentPage = 1
    var movieResponse: MovieResponse? = null

    init {
        getNowPlaying()

    }

    fun getNowPlaying() = viewModelScope.launch {
        nowPlaying.postValue(Resource.Loading())
        val response = repository.getNowPlaying(currentPage)
        nowPlaying.postValue(handleNowPlayingResponse(response))
    }

    private fun  handleNowPlayingResponse(response: Response<MovieResponse>): Resource<MovieResponse>{
        currentPage++
        if (response.isSuccessful){

            response.body()?.let {resultResponse->
                if (movieResponse == null){
                    movieResponse = resultResponse
                }else{
                    val oldState = movieResponse?.results
                    val newState = resultResponse.results
                    oldState?.addAll(newState)
                }
                return Resource.Success(movieResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Movie>> {
        return repository.searchDatabase(searchQuery, currentPage)
    }



}