package com.example.movieappmvvm.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.data.model.GenreResponse
import com.example.movieappmvvm.data.rest.ApiService
import com.example.movieappmvvm.data.rest.Retrofit
import com.example.movieappmvvm.repository.Repository
import com.example.movieappmvvm.util.AppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GenreViewModel @Inject constructor(private val repository: Repository): ViewModel()  {

    var genreLiveResponse: MutableLiveData<GenreResponse> = MutableLiveData()

    fun getGenreList():MutableLiveData<GenreResponse> {
        var resultData = repository.getGenreData()
        if (resultData != null) {
           genreLiveResponse = resultData
            return  genreLiveResponse
        } else {
            return genreLiveResponse
        }
    }


 }