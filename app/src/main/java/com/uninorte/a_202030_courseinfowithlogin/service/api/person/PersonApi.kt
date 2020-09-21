package com.uninorte.a_202030_courseinfowithlogin.service.api.person

import com.uninorte.a_202030_courseinfowithlogin.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PersonApi {
    @GET("{dbId}/professors/{professorId}")
    fun getProfessor(@Path("dbId") user: String, @Header("Authorization") header: String, @Path("professorId") professorId : String): Call<Person>

    @GET("{dbId}/students/{studentId}")
    fun getStudent(@Path("dbId") user: String, @Header("Authorization") header: String, @Path("studentId") studentId : String): Call<Person>
}