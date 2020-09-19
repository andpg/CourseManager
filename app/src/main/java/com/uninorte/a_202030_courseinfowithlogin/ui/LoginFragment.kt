package com.uninorte.a_202030_courseinfowithlogin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.User
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.CourseViewModel
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.LoginViewModel
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    val loginViewModel: LoginViewModel by activityViewModels()
    val courseViewModel: CourseViewModel by activityViewModels()
    var theToken : String = ""
    var userForCourse : String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseViewModel.getCourseData().observe(getViewLifecycleOwner(), Observer { users ->
            Log.d("MyOut", "Fragment  users list " + users.size)
        })


        view.findViewById<Button>(R.id.signInButton).setOnClickListener {
            val email  =  view.findViewById<EditText>(R.id.editEmail).text.toString()
            val clave = view.findViewById<EditText>(R.id.editPassword).text.toString()
            val usuario = email
            userForCourse = email
            loginViewModel.signIn(email,clave,usuario).observe(getViewLifecycleOwner(), Observer { user ->

                //Log.d("MyOut", "Fragment  signIn " + user + " error " + user.error)
                theToken = user.token
                if (user.token != "") {
                    Toast.makeText(context, "Token " + user.token, Toast.LENGTH_LONG).show()
                    courseViewModel.getCourses(email,theToken)
                    //findNavController().navigate(R.id.action_loginFragment_to_secondFragment)
                } else {
                    Toast.makeText(context, "Token failure " + user.error, Toast.LENGTH_LONG)
                        .show()
                }

            })
        }

        view.findViewById<Button>(R.id.signUpButton).setOnClickListener {
            val email =  view.findViewById<EditText>(R.id.editEmail).text.toString()
            val clave= view.findViewById<EditText>(R.id.editPassword).text.toString()
            val usuario = email

            loginViewModel.signUp(email,clave, usuario).observe(getViewLifecycleOwner(), Observer { user ->

                    Log.d("MyOut", "Fragment  signUp " + user + " error " + user.error)
                    theToken = user.token


            })
        }


        view.findViewById<Button>(R.id.buttonAddCourse).setOnClickListener {
            val usuario = userForCourse
            courseViewModel.addCourse(usuario,theToken)
        }


    }
}