package com.uninorte.a_202030_courseinfowithlogin.repository

import com.uninorte.a_202030_courseinfowithlogin.service.api.course_members.CourseMembersApiService

class CourseMembersRepository {
    private val service = CourseMembersApiService()

    fun getCourseMembersData() = service.getCourseMembersData()

    fun getCourseMembers(user: String, token: String, courseId: String) = service.getCourseMembers(user,token,courseId)

    fun addStudent(user: String, token: String, courseId: String) = service.addStudent(user,token,courseId)
}