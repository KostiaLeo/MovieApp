package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.NetworkMovie
import com.example.movieapp.model.network.data.PhotoId
import com.example.movieapp.model.network.data.Small
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkSource @Inject constructor(private val api: MovieApi) {
    suspend fun retrieveData(category: String, key: String, language: String,   page: Int): List<NetworkMovie> = withContext(Dispatchers.IO) {
        val playList = api.getPropertyAsync(category, key, language, page)
       playList.networkMovie
    }
}

class SmallNetworkSource @Inject constructor(private val api: MovieApi) {
    suspend fun smallRetrieveData(category: String, key: String, language: String,  page: Int): List<Small> = withContext(Dispatchers.IO) {
        val playList = api.getSmallList(category, key, language, page)
        playList.small
    }
}

class MovieInfoSource @Inject constructor(private val api: MovieApi){
    suspend fun retrieveInfoData(id: Int): MovieInfo = withContext(Dispatchers.IO){
        val infoOfMovie = api.getMovieByID(id)
        infoOfMovie
    }
}