package com.alxena.pronunciationtrainer.data.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIInstance {
    var ConnectionUrl: String = "http://10.0.2.2:5000"
    val service: APIController by lazy{
        Retrofit
            .Builder().baseUrl(ConnectionUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIController::class.java)
    }
}