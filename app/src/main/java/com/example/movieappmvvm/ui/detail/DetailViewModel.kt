package com.example.movieappmvvm.ui.detail

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.data.model.DetailMovieResponse
import com.example.movieappmvvm.data.rest.ApiService
import com.example.movieappmvvm.data.rest.Retrofit
import com.example.movieappmvvm.repository.Repository
import com.example.movieappmvvm.util.AppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var title: ObservableField<String> = ObservableField()
    var genre: ObservableField<String> = ObservableField()
    var status: ObservableField<String> = ObservableField()
    var released: ObservableField<String> = ObservableField()
    var deskripsi: ObservableField<String> = ObservableField()

    fun getDetailList(movieId: Int) {
        var resultData = repository.getDetailData(movieId)
        if (resultData != null) {
            setData(resultData)
        } else {

        }
    }

    fun setData(data: MutableLiveData<DetailMovieResponse>) {
        Log.e("message", "dataku ${data.value}")
        title.set(data?.value?.title)
        genre.set(data?.value?.status)
        status.set(data?.value?.status)
        released.set(data?.value?.releaseDate)
        deskripsi.set(data?.value?.overview)
    }
}