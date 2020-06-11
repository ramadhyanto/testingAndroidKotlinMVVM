package com.example.movieappmvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.repository.Repository
import javax.inject.Inject

class MyViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    fun getDataViewModel() :String {
        val mydaggername = repository.getData()
        return mydaggername
    }
}