package com.example.lazycoloumntugas2.repository

import com.example.lazycoloumntugas2.data.model.User
import com.example.lazycoloumntugas2.data.network.RetrofitInstance

class UserRepository {
    suspend fun getUsers(): List<User> {
        return RetrofitInstance.api.getUsers()
    }
}