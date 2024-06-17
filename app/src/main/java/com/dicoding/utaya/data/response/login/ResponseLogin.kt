package com.dicoding.utaya.data.response.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("data")
	val dataLogin: DataLogin?,

	@field:SerializedName("message")
	val message: String
)