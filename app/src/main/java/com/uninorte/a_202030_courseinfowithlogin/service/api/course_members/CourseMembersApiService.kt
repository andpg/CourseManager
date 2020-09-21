package com.uninorte.a_202030_courseinfowithlogin.service.api.course_members

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.CourseMembers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CourseMembersApiService {
    companion object{
        val theResponse = MutableLiveData<CourseMembers>()

        fun getRestEngine(): CourseMembersApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(CourseMembersApi::class.java)

        }
    }

    fun getCourseMembersData() = theResponse

    fun getCourseMembers(user: String, token: String, courseId: String) {
        Log.d("MyOut", "getCourse <$courseId> with token  <$token>")
        val auth = "Bearer $token"
        getRestEngine().getCourseMembers(user,auth,courseId).enqueue(object: Callback<CourseMembers> {
            override fun onResponse(call: Call<CourseMembers>, response: Response<CourseMembers>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val courseResponse = response.body()
                    if (courseResponse != null) {
                        theResponse.postValue(courseResponse)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<CourseMembers>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
}