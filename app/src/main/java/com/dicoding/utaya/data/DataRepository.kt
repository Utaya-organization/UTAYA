package com.dicoding.utaya.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.utaya.data.api.ApiService
import com.dicoding.utaya.data.response.login.ResponseLogin

class DataRepository(private val apiService: ApiService) {

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

}