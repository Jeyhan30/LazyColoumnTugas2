package com.example.lazycoloumntugas2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lazycoloumntugas2.data.model.User
import com.example.lazycoloumntugas2.data.network.ApiService
import kotlinx.coroutines.launch

class UserDetailViewModel(private val apiService: ApiService) : ViewModel() {
    var userDetail by mutableStateOf<User?>(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun fetchUserDetail(userId: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                userDetail = apiService.getUserDetail(userId)
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

class UserDetailViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
