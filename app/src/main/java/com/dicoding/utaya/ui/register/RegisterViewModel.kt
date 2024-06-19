package com.dicoding.utaya.ui.register

import androidx.lifecycle.ViewModel
import com.dicoding.utaya.data.DataRepository

class RegisterViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun register(username: String, password: String, confirmPassword: String) = dataRepository.postRegister(username, password, confirmPassword)
}
