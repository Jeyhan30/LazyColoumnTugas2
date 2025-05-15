package com.example.lazycoloumntugas2.data.network

import com.example.lazycoloumntugas2.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): User
}
