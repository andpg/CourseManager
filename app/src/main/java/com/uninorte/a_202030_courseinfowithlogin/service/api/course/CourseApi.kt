package com.uninorte.a_202030_courseinfowithlogin.service.api.course

import com.uninorte.a_202030_courseinfowithlogin.model.Course
import retrofit2.Call
import retrofit2.http.*

interface CourseApi {

    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<Course>


}