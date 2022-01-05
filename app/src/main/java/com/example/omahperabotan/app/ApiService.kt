package com.example.omahperabotan.app

import com.example.omahperabotan.model.ResponModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") nama : String,
        @Field("email") email : String,
        @Field("phone") phone : String,
        @Field("password") pass : String,
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") pass : String,
    ):Call<ResponModel>
}