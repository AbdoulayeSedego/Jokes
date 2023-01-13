package com.example.jokes

import retrofit2.Response
import retrofit2.http.GET

interface JokesAPI {
    @GET("/random_joke")
    suspend fun getJokes(): Response<List<Jokes>>
}