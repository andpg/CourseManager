package com.uninorte.a_202030_courseinfowithlogin.service.api.course

import com.uninorte.a_202030_courseinfowithlogin.model.Course
import com.uninorte.a_202030_courseinfowithlogin.model.NewStudent
import com.uninorte.a_202030_courseinfowithlogin.model.RestartChecker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CourseApi {

    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<Course>

    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<NewStudent>

    @GET("restart")
    fun restart(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<RestartChecker>

}