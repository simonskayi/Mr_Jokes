package com.kwekboss.mrjokes.api


import retrofit2.Response
import retrofit2.http.*


interface JokeRequest {
    @GET("Any?type=twopart")

 suspend fun getAllJokes(): Response<MrJoke>
}