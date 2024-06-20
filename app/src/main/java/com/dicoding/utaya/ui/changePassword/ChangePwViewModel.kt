package com.dicoding.utaya.ui.changePassword

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.utaya.data.DataRepository
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.utils.Preference
import kotlinx.coroutines.Dispatchers

class ChangePwViewModel(private val context: Context, private val dataRepository: DataRepository) : ViewModel() {

    fun changePw(password: String, newPassword: String, confirmNewPassword: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val token = Preference.getToken(context) ?: throw Exception("Token tidak ditemukan")
            Log.d("ChangePwViewModel", "Token: $token, Password: $password, New Password: $newPassword, Confirm New Password: $confirmNewPassword")
            val response = dataRepository.putPassword(token, password, newPassword, confirmNewPassword)
            Log.d("ChangePwViewModel", "Response from repository: ${response.value}")

            emit(response.value) // Emit the response's value
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}
