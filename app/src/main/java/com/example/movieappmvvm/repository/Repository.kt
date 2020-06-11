package com.example.movieappmvvm.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieappmvvm.data.model.DetailMovieResponse
import com.example.movieappmvvm.data.model.GenreResponse
import com.example.movieappmvvm.data.model.MovieResponse
import com.example.movieappmvvm.data.rest.ApiService
import com.example.movieappmvvm.data.rest.Retrofit
import com.example.movieappmvvm.util.AppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor() {

    var genreLiveData: MutableLiveData<GenreResponse> = MutableLiveData()
    var movieLiveData: MutableLiveData<ArrayList<MovieResponse?>> = MutableLiveData()
    var detailLiveData: MutableLiveData<DetailMovieResponse> = MutableLiveData()

    fun getData(): String {
        return "This is Dagger MVVM"
    }

    fun getGenreData(): MutableLiveData<GenreResponse> {
        var apiInterface: ApiService = Retrofit.apiClient.create(ApiService::class.java)
        var genreList = apiInterface.getAllGenreMovie(AppConfig.apiKey)
        genreList.enqueue(object : Callback<GenreResponse> {
            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                genreLiveData.value = response.body()

            }
        })
        return genreLiveData
    }

    fun getMovieData(genreId: Int?, page: Int?): MutableLiveData<ArrayList<MovieResponse?>> {
        Log.e("meesage","masuk sini 2")
        var apiInterface: ApiService = Retrofit.apiClient.create(ApiService::class.java)
        var movieList = apiInterface.getAllMovie(genreId!!, AppConfig.apiKey, page!!)
        movieList.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.e("message","masuk sini 3 ${response.body()}")
                movieLiveData?.value?.add(response.body())

            }
        })

        return movieLiveData
    }

    fun getDetailData(movieId: Int): MutableLiveData<DetailMovieResponse> {
        var apiInterface: ApiService = Retrofit.apiClient.create(ApiService::class.java)
        Log.e("message", "MovieId $movieId")
        var detailList = apiInterface.getDetailMovie(movieId, AppConfig.apiKey)
        detailList.enqueue(object : Callback<DetailMovieResponse> {
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                Log.e("message", "onResponse Detail ${response.body()}")
                detailLiveData?.value = response.body()
            }
        })

        return detailLiveData
    }
}