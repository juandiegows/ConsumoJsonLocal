package com.example.consumojsonlocal.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CustomRetrofit {
    var retrofit  = Retrofit.Builder()
        .baseUrl("http://192.168.10.105:3115/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(IService::class.java)

}