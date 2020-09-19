package com.uninorte.a_202030_courseinfowithlogin.repository

import com.uninorte.a_202030_courseinfowithlogin.service.api.course.CourseApiService

class CourseRepository {

    private val service = CourseApiService()

    fun getCourses(user: String, token: String) = service.getCourses(user,token)

    fun addCourse(user: String, token: String) = service.addCourse(user,token)

    fun getCourseData() = service.getCourseData()
}