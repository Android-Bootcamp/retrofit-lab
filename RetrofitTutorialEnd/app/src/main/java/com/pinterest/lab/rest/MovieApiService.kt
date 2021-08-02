package com.pinterest.lab.rest

import com.pinterest.lab.model.Movie
import com.pinterest.lab.model.TopRatedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Single<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Single<TopRatedResponse>
}