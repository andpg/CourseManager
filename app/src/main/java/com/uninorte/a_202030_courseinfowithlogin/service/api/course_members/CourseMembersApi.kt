package com.uninorte.a_202030_courseinfowithlogin.service.api.course_members

import com.uninorte.a_202030_courseinfowithlogin.model.CourseMembers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseMembersApi {
    @GET("{dbId}/courses/{courseId}")
    fun getCourseMembers(@Path("dbId") user: String, @Header("Authorization") header: String, @Path("courseId") courseId: String): Call<CourseMembers>
}