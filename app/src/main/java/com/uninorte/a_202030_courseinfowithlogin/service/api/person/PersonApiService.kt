package com.uninorte.a_202030_courseinfowithlogin.service.api.person

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.Person
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonApiService {
    companion object{
        val theResponse = MutableLiveData<Person>()

        fun getRestEngine(): PersonApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(PersonApi::class.java)

        }
    }

    fun getPersonData() = theResponse

    fun getProfessor(user: String, token: String, professorId: String) {
        Log.d("MyOut", "getProfessor <$professorId> with token  <$token>")
        val auth = "Bearer $token"
        getRestEngine().getProfessor(user,auth,professorId).enqueue(object: Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val personResponse = response.body()
                    if (personResponse != null) {
                        theResponse.postValue(personResponse)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun getStudent(user: String, token: String, studentId: String) {
        Log.d("MyOut", "getStudent <$studentId> with token  <$token>")
        val auth = "Bearer $token"
        getRestEngine().getStudent(user,auth,studentId).enqueue(object: Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val personResponse = response.body()
                    if (personResponse != null) {
                        theResponse.postValue(personResponse)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
}