package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_courseinfowithlogin.repository.PersonRepository

class PersonViewModel : ViewModel() {
    private val repository = PersonRepository()

    fun getPersonData() = repository.getPersonData()

    fun getProfessor(user: String, token: String, professorId: String) = repository.getProfessor(user,token,professorId)

    fun getStudent(user: String, token: String, studentId: String) = repository.getStudent(user,token,studentId)
}