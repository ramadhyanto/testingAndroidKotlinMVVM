package com.example.movieappmvvm.data.rest

import com.example.movieappmvvm.data.model.MovieResponse
import com.example.movieappmvvm.data.model.DetailMovieResponse
import retrofit2.Call
import com.example.movieappmvvm.data.model.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface ApiService {

    @GET("genre/movie/list?")
    fun getAllGenreMovie(@Query("api_key") api_key: String): Call<GenreResponse>

    @GET("movie/{genreId}/lists?")
    fun getAllMovie(
        @Path("genreId", encoded = true) genreId: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{movieId}?")
    fun getDetailMovie(
        @Path("movieId", encoded = true) movieId: Int,
        @Query("api_key") api_key: String
    ): Call<DetailMovieResponse>
}