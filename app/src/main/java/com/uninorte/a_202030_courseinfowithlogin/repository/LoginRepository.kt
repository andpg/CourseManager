package com.uninorte.a_202030_courseinfowithlogin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.User
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApiService

class LoginRepository {

    var userLiveData = MutableLiveData<User>()

    private val service = LoginApiService()

    fun signIn(user: User) = service.signIn(user)

    fun signUp(user: User) = service.signUp(user)

    fun checkToken(token: String) = service.checkToken(token)

    fun getUser() = userLiveData as LiveData<User>

}