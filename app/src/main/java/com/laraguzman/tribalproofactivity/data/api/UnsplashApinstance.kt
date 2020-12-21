package com.laraguzman.tribalproofactivity.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UnsplashApinstance {
    companion object {
        val BASE_URL : String = "https://api.unsplash.com/"

    }

    fun GetInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}