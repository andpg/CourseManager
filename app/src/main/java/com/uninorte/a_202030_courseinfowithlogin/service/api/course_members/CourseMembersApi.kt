package com.uninorte.a_202030_courseinfowithlogin.service.api.course_members

import com.uninorte.a_202030_courseinfowithlogin.model.CourseMembers
import com.uninorte.a_202030_courseinfowithlogin.model.NewStudent
import retrofit2.Call
import retrofit2.http.*

interface CourseMembersApi {
    @GET("{dbId}/courses/{courseId}")
    fun getCourseMembers(@Path("dbId") user: String, @Header("Authorization") header: String, @Path("courseId") courseId: String): Call<CourseMembers>

    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String, @Header ("Authorization") header: String, @Field("courseId") courseId: String): Call<NewStudent>
}