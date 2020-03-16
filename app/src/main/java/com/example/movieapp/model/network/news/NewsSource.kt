package com.example.movieapp.model.network.news

import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.model.network.news.data.Result
import com.example.movieapp.utils.ALL_NEWS
import com.example.movieapp.utils.DAILY_NEWS
import com.example.movieapp.utils.WEEKLY_NEWS
import com.example.movieapp.utils.ioReturnTask
import javax.inject.Inject

class NewsSource @Inject constructor(private val api: NewsApi) {

    suspend fun getDailyNews(type: String = ALL_NEWS): List<SmallMovieList> {
        val newsResponse = api.getNews(type, DAILY_NEWS)

        return if (newsResponse.smallMovieList.isNullOrEmpty()) {
            emptyList()
        } else {
            newsResponse.smallMovieList
        }
    }

    suspend fun getWeeklyNews(type: String = ALL_NEWS): List<SmallMovieList> {
        val newsResponse = api.getNews(type, WEEKLY_NEWS)

        return if (newsResponse.smallMovieList.isNullOrEmpty()) {
            emptyList()
        } else {
            newsResponse.smallMovieList
        }
    }
}