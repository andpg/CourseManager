package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_courseinfowithlogin.model.User
import com.uninorte.a_202030_courseinfowithlogin.repository.LoginRepository

class LoginViewModel : ViewModel(){

    var userLiveData = MutableLiveData<User>()


    private val repository = LoginRepository()

    fun signIn(email: String, clave: String, usuario : String) =
        repository.signIn(User(email, clave, usuario, usuario,"",""))

    fun signUp(email: String, clave: String, usuario : String) =
        repository.signUp(User(email, clave, usuario, usuario,"",""))

    fun getUser() = userLiveData

    fun checkToken(token: String) = repository.checkToken(token)
}