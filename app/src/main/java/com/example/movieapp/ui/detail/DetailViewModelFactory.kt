package com.example.movieapp.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.model.network.NetworkMovie


class DetailViewModelFactory(private val movie: NetworkMovie) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}