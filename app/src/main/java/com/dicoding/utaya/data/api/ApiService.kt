package com.dicoding.utaya.data.api

import com.dicoding.utaya.data.response.login.ResponseLogin
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : ResponseLogin
}