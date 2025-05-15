package com.example.lazycoloumntugas2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycoloumntugas2.data.model.User
import com.example.lazycoloumntugas2.data.network.RetrofitInstance
import com.example.lazycoloumntugas2.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users
    private val repository = UserRepository()

    init {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUsers()
            _users.value = response
        }
    }

    fun getUserById(id: Int): User? {
        return _users.value.find { it.id == id }
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                _users.value = repository.getUsers()
            } catch (e: Exception) {
                // Error handling bisa ditambahkan di sini
            }
        }
    }
}