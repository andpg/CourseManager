package com.uninorte.a_202030_courseinfowithlogin.service.api.login

import com.uninorte.a_202030_courseinfowithlogin.model.Checker
import com.uninorte.a_202030_courseinfowithlogin.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @POST("signup/")
    fun signUp(@Body user: User): Call<User>

    @POST("signin/")
    fun signIn(@Body user: User): Call<User>

    @FormUrlEncoded
    @POST("signup/")
    fun signUp2(@Field("email") email: String,@Field("password") password: String,@Field("username") username: String,@Field("name") name: String ): Call<ResponseBody>

    @POST("check/token/")
    fun checkToken(@Header("Authorization") header: String): Call<Checker>

}