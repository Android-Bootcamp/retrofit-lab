package com.pinterest.lab.util

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pinterest.lab.rest.ApiKey
import com.pinterest.lab.rest.RestProvider
import retrofit2.Retrofit
import java.lang.IllegalStateException

/**
 * Obtains the Retrofit client. Note: in a better example, we'd want to request
 */
fun Fragment.getRetrofit(): Retrofit {
    val activity = this.activity
    if (activity is RestProvider) {
        return activity.provideRetrofit()
    } else {
        throw IllegalStateException("You are calling from a Fragment whose host Activity does not " +
            "implement RestProvider")
    }
}

/**
 * Obtains the API key
 */
fun Fragment.getApiKey(): String {
    val activity = this.activity
    if (activity is ApiKey) {
        return activity.provideApiKey()
    } else {
        throw IllegalStateException("You are calling from a Fragment whose host Activity does not " +
            "implement ApiKey")
    }
}

private const val IMG_URL = "https://image.tmdb.org/t/p/w500"
/**
 * Obtains the URL where the movie images are hosted
 */
fun RecyclerView.Adapter<*>.getImageUrl(movieHost: String?): String? {
    return if (movieHost.isNullOrEmpty()) {
        movieHost
    } else {
        IMG_URL + movieHost
    }
}