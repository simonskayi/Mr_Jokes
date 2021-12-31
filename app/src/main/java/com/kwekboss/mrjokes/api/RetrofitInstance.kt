package com.kwekboss.mrjokes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    const val Base_URL = "https://v2.jokeapi.dev/joke/"
    val retrofit = Retrofit.Builder()
        .baseUrl(Base_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokeRequest::class.java)

}