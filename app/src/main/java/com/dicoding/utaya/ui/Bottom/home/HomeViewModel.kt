package com.dicoding.utaya.ui.Bottom.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.utaya.data.DataRepository
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.response.produk.RecommendationsItem
import kotlinx.coroutines.launch

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private val _listProduk = MutableLiveData<List<RecommendationsItem>>()
    val listProduk: LiveData<List<RecommendationsItem>> = _listProduk

    fun fetchProduk() {
        viewModelScope.launch {
            try {
                dataRepository.getProduk().collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val responseProdukItem = result.data
                            val recommendations = responseProdukItem.dataTypeSkin.recommendations
                            _listProduk.value = recommendations ?: emptyList()
                        }
                        is Result.Error -> {
                            Log.e("HomeViewModel", "Error fetching produk: ${result}")
                        }
                        is Result.Loading -> {
                            // Handle loading state if needed
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception fetching produk: ${e.message}")
            }
        }
    }
}
