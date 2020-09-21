package com.uninorte.a_202030_courseinfowithlogin.repository

import com.uninorte.a_202030_courseinfowithlogin.service.api.person.PersonApiService

class PersonRepository {
    private val service = PersonApiService()

    fun getPersonData() = service.getPersonData()

    fun getProfessor(user: String, token: String, professorId: String) = service.getProfessor(user,token,professorId)

    fun getStudent(user: String, token: String, studentId: String) = service.getStudent(user,token,studentId)
}