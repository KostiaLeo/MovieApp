package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.PhotoId
import com.example.movieapp.model.network.data.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//A retrofit service to fetch movie playlist.
interface MovieApi {
    @GET("{category}")
    suspend fun getPropertyAsync(
        @Path("category") category: String,
        @Query("api_key") key: String = "26f381d6ab8dd659b22d983cab9aa255",
        @Query("language") language: String,
        @Query("page") page: Int
    ): Results


    @GET("{category}")
    suspend fun getSmallList(
        @Path("category") category: String,
        @Query("api_key") key: String = "26f381d6ab8dd659b22d983cab9aa255",
        @Query("language") language: String,
        @Query("page") page: Int
    ): PhotoId


    @GET("{id}?api_key=26f381d6ab8dd659b22d983cab9aa255&language=ru")
    suspend fun getMovieByID(@Path("id") id: Int): MovieInfo
}