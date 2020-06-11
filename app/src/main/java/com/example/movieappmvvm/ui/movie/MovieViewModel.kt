package com.example.movieappmvvm.ui.movie

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.data.model.MovieResponse
import com.example.movieappmvvm.data.model.ResultsItem
import com.example.movieappmvvm.data.rest.ApiService
import com.example.movieappmvvm.data.rest.Retrofit
import com.example.movieappmvvm.repository.Repository
import com.example.movieappmvvm.util.AppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel@Inject constructor(private val repository: Repository): ViewModel() {
    var movieLiveResponse: MutableLiveData<ArrayList<MovieResponse?>> = MutableLiveData()
    fun getMovieList(genreId:Int,page:Int): MutableLiveData<MovieResponse> {
        var resultData = repository.getMovieData(genreId, page)
        if (resultData != null) {
            if(page == 1) {
                Log.e("meesage","masuk sini")
                movieLiveResponse = resultData
            }

            return MutableLiveData()
        } else {
            return MutableLiveData()
        }

    }
}