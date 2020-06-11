package com.example.movieappmvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmvvm.ui.detail.DetailViewModel
import com.example.movieappmvvm.ui.genre.GenreViewModel
import javax.inject.Inject
import javax.inject.Provider

class DetailViewModelFactory @Inject constructor(private val myviewmodelprovider: Provider<DetailViewModel>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return myviewmodelprovider.get() as T
    }
}