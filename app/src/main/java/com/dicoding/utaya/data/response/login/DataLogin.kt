package com.dicoding.utaya.data.response.login

import com.google.gson.annotations.SerializedName

data class DataLogin(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("token")
	val token: String
)