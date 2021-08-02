package com.pinterest.lab.rest

import retrofit2.Retrofit

interface RestProvider {
    fun provideRetrofit(): Retrofit
}