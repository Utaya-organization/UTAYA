package com.dicoding.utaya.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.utaya.data.api.ApiService
import com.dicoding.utaya.data.response.changePw.ResponseChangePw
import com.dicoding.utaya.data.response.login.ResponseLogin
import com.dicoding.utaya.data.response.register.ResponseRegister

class DataRepository(private val apiService: ApiService) {

    fun postRegister(username: String, password: String, confirmPassword: String): LiveData<Result<ResponseRegister>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postRegister(username, password, confirmPassword)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "postSignUp: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postLogin(username: String, password: String): LiveData<Result<ResponseLogin>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postLogin(username, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "postLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun putPassword(token: String, password: String, newPassword: String, confirmNewPassword: String): LiveData<Result<ResponseChangePw>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.putPassword(token, password, newPassword, confirmNewPassword)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("DataRepository", "putPassword: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}
