package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_courseinfowithlogin.repository.CourseRepository

class CourseViewModel : ViewModel() {

    private val repository = CourseRepository()

    fun getCourses(user: String, token: String) = repository.getCourses(user, token)

    fun addCourse(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
         repository.addCourse(user, token)
        
    }

    fun restart(user: String, token: String) = repository.restart(user,token)

    fun getCourseData() = repository.getCourseData()

}
