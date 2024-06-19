package com.dicoding.utaya.data.api

import com.dicoding.utaya.data.response.changePw.ResponseChangePw
import com.dicoding.utaya.data.response.login.ResponseLogin
import com.dicoding.utaya.data.response.register.ResponseRegister
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @FormUrlEncoded
    @POST("users")
    suspend fun postRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String,
    ) : ResponseRegister
    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : ResponseLogin

    @FormUrlEncoded
    @PUT("users")
    suspend fun putPassword(
        @Header("Authorization") token: String,
        @Field("password") password: String,
        @Field("newPassword") newPassword: String,
        @Field("confirmNewPassword") confirmNewPassword: String,
    ) : ResponseChangePw
}