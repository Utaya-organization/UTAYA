package com.dicoding.utaya.ui.changePassword

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.utaya.data.DataRepository
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.response.changePw.ResponseChangePw
import com.dicoding.utaya.data.utils.Preference
import kotlinx.coroutines.Dispatchers

class ChangePwViewModel(private val context: Context, private val dataRepository: DataRepository) : ViewModel() {

    fun changePw(password: String, newPassword: String, confirmNewPassword: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val token = Preference.getToken(context) ?: throw Exception("Token tidak ditemukan")
            val response = dataRepository.putPassword(token, password, newPassword, confirmNewPassword)
            emit(response.value) // Emit the response's value
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}
