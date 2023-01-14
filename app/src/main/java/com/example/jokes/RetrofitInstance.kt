package com.example.jokes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val api : JokesAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesAPI::class.java)
    }
}