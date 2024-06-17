package com.dicoding.utaya.ui.login

import androidx.lifecycle.ViewModel
import com.dicoding.utaya.data.DataRepository

class LoginViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun login(username: String, password: String) = dataRepository.postLogin(username, password)
}