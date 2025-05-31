package com.alxena.pronunciationtrainer.data.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIInstance {
    var ConnectionUrl: String = "http://10.0.2.2:5000"
    val service: APIController by lazy {
        Retrofit
            .Builder().baseUrl(ConnectionUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIController::class.java)
    }
    private var access_token: String = "";
    var refresh_token: String = "";
    fun refresh(): String {
        val tokens = service.refresh("Bearer ${refresh_token}").execute().body()
        access_token = tokens?.access_token?:""
        refresh_token = tokens?.refresh_token?:""
        return refresh_token
    }
    fun getHeader():String {
        return "Bearer ${access_token}"
    }
}