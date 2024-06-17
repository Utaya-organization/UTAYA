package com.dicoding.utaya.data.di

import android.content.Context
import com.dicoding.utaya.data.DataRepository
import com.dicoding.utaya.data.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val apiService = ApiConfig.getApiService(context)
        return DataRepository(apiService)
    }
}