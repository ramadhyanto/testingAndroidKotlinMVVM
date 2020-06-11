package com.example.movieappmvvm.data.rest

import com.example.movieappmvvm.util.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object Factory {
        val BASE_URL = AppConfig.BASE_URL
        private var retrofit: Retrofit? = null
        val apiClient: Retrofit
            get() {
                if (retrofit == null) {
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .build()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit!!
            }
    }


}