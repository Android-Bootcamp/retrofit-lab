package com.pinterest.lab.util

import androidx.fragment.app.Fragment
import com.pinterest.lab.rest.ApiKey
import com.pinterest.lab.rest.RestProvider
import retrofit2.Retrofit
import java.lang.IllegalStateException

fun Fragment.getRetrofit(): Retrofit {
    val activity = this.activity
    if (activity is RestProvider) {
        return activity.provideRetrofit()
    } else {
        throw IllegalStateException("You are calling from a Fragment whose host Activity does not " +
            "implement RestProvider")
    }
}

fun Fragment.getApiKey(): String {
    val activity = this.activity
    if (activity is ApiKey) {
        return activity.provideApiKey()
    } else {
        throw IllegalStateException("You are calling from a Fragment whose host Activity does not " +
            "implement ApiKey")
    }
}