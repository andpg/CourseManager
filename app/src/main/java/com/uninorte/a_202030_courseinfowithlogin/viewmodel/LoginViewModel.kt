package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import androidx.lifecycle.*
import com.uninorte.a_202030_courseinfowithlogin.repository.LoginRepository
import com.uninorte.a_202030_courseinfowithlogin.model.User

class LoginViewModel : ViewModel(){

    var userLiveData = MutableLiveData<User>()


    private val repository = LoginRepository()

    fun signIn(email: String, clave: String, usuario : String) =
        repository.signIn(User(email, clave, usuario, usuario,"",""))

    fun signUp(email: String, clave: String, usuario : String) =
        repository.signUp(User(email, clave, usuario, usuario,"",""))

    fun getUser() = userLiveData

}