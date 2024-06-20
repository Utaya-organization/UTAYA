package com.dicoding.utaya.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.utaya.data.api.ApiService
import com.dicoding.utaya.data.response.changePw.ResponseChangePw
import com.dicoding.utaya.data.response.login.ResponseLogin
import com.dicoding.utaya.data.response.produk.ResponseProdukItem
import com.dicoding.utaya.data.response.register.ResponseRegister
import com.dicoding.utaya.data.utils.Preference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository(private val apiService: ApiService, private val context: Context) {

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
        Log.d("ppp repo", token)
        try {
            val response = apiService.putPassword(token, password, newPassword, confirmNewPassword)
            Log.d("ppp repo", response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("DataRepository", "putPassword: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getProduk(): Flow<Result<ResponseProdukItem>> = flow {
        emit(Result.Loading)
        try {
            val token = Preference.getToken(context) ?: throw Exception("Token tidak ditemukan")
            Log.d("cek response" , "$token")
            val bearerToken = token
            val response = apiService.getProduk(bearerToken)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}
