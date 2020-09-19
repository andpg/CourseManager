package com.uninorte.a_202030_courseinfowithlogin.service.api.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class LoginApiService {

    companion object{

        fun getRestEngine(): LoginApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            //val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(LoginApi::class.java)

        }
    }


     fun signIn(user: User) : MutableLiveData<User>{
        val userResponse = MutableLiveData<User>()

         getRestEngine().signIn(user).enqueue(object: Callback<User>{
             override fun onResponse(call: Call<User>, response: Response<User>) {
                 if (response.isSuccessful) {
                     Log.d("MyOut", "OK isSuccessful " + response.body())
                     val loginResponse = response.body()
                     if (loginResponse != null) {
                         user.token = loginResponse.token
                         Log.d("MyOut", "OK isSuccessful token " + user.token)
                         userResponse.value = user
                     }
                 } else {
                     Log.d("MyOut", "NOK  "+response.code() )
                     user.error = response.errorBody().toString()
                     user.token = ""
                     userResponse.value = user
                 }
             }

             override fun onFailure(call: Call<User>, t: Throwable) {
                 Log.d("MyOut","Failure "+t.message)
             }

         })

        return userResponse
    }

    fun signUp(user: User) : MutableLiveData<User> {
        val userResponse = MutableLiveData<User>()

        getRestEngine().signUp(user).enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body())
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        user.token = loginResponse.token
                        Log.d("MyOut", "OK isSuccessful token " + user.token)
                        userResponse.value = user
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    user.error = response.errorBody().toString()
                    user.token = ""
                    userResponse.value = user
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

        return userResponse
    }

    suspend fun signUp2(user: User) {
        getRestEngine().signUp2(user.email, user.password, user.username, user.name).enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body()?.string())
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    // Log.d("MyOut", "NOK isNotSuccessful " + response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }
}