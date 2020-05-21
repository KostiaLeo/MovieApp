package com.example.movieapp.ui.home.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.ParentListMovie
import com.example.movieapp.model.network.data.SmallMovieList
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallMovieListSource) : ViewModel() {

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<SmallMovieList>()
    val navigateToSelectProperty: LiveData<SmallMovieList>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    //LiveData of Top Rated movies
    private var _parentListMovie = MutableLiveData<List<ParentListMovie>>()
    val parentListMovie: LiveData<List<ParentListMovie>>
        get() = _parentListMovie


    val errorClickListener = View.OnClickListener { fetchMoviesLists() }

    init {
        println("initialization viewModel")
        fetchMoviesLists()
    }


    private fun fetchMoviesLists() {

        viewModelScope.launch {
            try {

                _parentListMovie.value = listOf(
                    ParentListMovie(
                        "Топ рейтинг","top_rated",
                        networkSource.fetchSmallMovieList(
                            "top_rated",
                            "26f381d6ab8dd659b22d983cab9aa255",
                            "ru"
                        )
                    ),
                    ParentListMovie(
                        "Популярное","popular",
                        networkSource.fetchSmallMovieList(
                            "popular",
                            "26f381d6ab8dd659b22d983cab9aa255",
                            "ru"
                        )
                    ),
                    ParentListMovie(
                        "Сейчас в кино","now_playing",
                        networkSource.fetchSmallMovieList(
                            "now_playing",
                            "26f381d6ab8dd659b22d983cab9aa255",
                            "ru"
                        )
                    )
                )

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                if (parentListMovie.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }

    }


    fun displayPropertyDetails(movie: SmallMovieList) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}