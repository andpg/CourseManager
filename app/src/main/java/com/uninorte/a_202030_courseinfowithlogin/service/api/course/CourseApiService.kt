package com.uninorte.a_202030_courseinfowithlogin.service.api.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import com.uninorte.a_202030_courseinfowithlogin.model.NewStudent
import com.uninorte.a_202030_courseinfowithlogin.model.RestartChecker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CourseApiService {

    companion object{

        val theResponse = MutableLiveData<List<Course>>()
        var courses = mutableListOf<Course>()

        fun getRestEngine(): CourseApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(CourseApi::class.java)

        }
    }

    fun getCourseData() = theResponse


     fun getCourses(user: String, token: String){

        //Log.d("MyOut", "getCourses with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine().getCourses(user,auth).enqueue(object: Callback<List<Course>>{
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        theResponse.postValue(response.body())
                        courses.clear()
                        val t = response.body() as List<Course>
                        courses.addAll(t)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

    }

    fun addCourse(user: String, token: String) {

        Log.d("MyOut", "addCourse with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine().addCourse(user,auth).enqueue(object: Callback<Course>{
            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //Log.d("MyOut", "OK isSuccessful token " )
                        courses.add(response.body()!!)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<Course>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun addStudent(user: String, token: String) {
        Log.d("MyOut", "addStudent with token  <$token>")
        val auth = "Bearer $token"
        getRestEngine().addStudent(user,auth).enqueue(object: Callback<NewStudent>{
            override fun onResponse(call: Call<NewStudent>, response: Response<NewStudent>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val studentResponse = response.body()
                    if (studentResponse != null) {
                        val course = courses.first { course -> course.id == studentResponse.course_id }
                        course.students = (course.students.toInt() + 1).toString()
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<NewStudent>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun restart(user: String, token: String) {
        Log.d("MyOut", "restart db with token  <$token>")
        val auth = "Bearer "+token
        getRestEngine().restart(user,auth).enqueue(object: Callback<RestartChecker>{
            override fun onResponse(call: Call<RestartChecker>, response: Response<RestartChecker>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val restartResponse = response.body()
                    if (restartResponse != null) {
                        if(restartResponse.result) {
                            courses.clear()
                            theResponse.postValue(courses)
                        }
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<RestartChecker>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

}