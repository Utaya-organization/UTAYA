package com.dicoding.utaya.ui.Bottom.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is camera"
    }
    val text: LiveData<String> = _text
}