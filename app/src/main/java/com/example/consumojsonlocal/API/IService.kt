package com.example.consumojsonlocal.API

import com.example.consumojsonlocal.models.User
import retrofit2.Call
import retrofit2.http.GET

interface IService {
    @GET("api/Student")
    fun getUsers(): Call<List<User>>
}