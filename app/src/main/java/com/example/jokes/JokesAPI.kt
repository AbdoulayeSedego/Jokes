package com.example.jokes

import retrofit2.Response
import retrofit2.http.GET

interface JokesAPI {
    @GET
    suspend fun getJokes(): Response<List<Jokes>>
}