package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_courseinfowithlogin.repository.CourseMembersRepository

class CourseMembersViewModel : ViewModel() {
    private val repository = CourseMembersRepository()

    fun getCourseMembersData() = repository.getCourseMembersData()

    fun getCourseMembers(user: String, token: String, courseId: String) = repository.getCourseMembers(user,token,courseId)
}