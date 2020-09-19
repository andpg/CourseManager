package com.uninorte.a_202030_courseinfowithlogin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.CourseViewModel
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.LoginViewModel
import androidx.navigation.fragment.findNavController
import com.uninorte.a_202030_courseinfowithlogin.model.User
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    val loginViewModel: LoginViewModel by activityViewModels()
    val courseViewModel: CourseViewModel by activityViewModels()
    val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun saveTokenAndGoHome(user: User) {
            if (user.token != "") {
                with(sharedPreferences.edit()) {
                    putString("token",user.token)
                    putString("usuario",user.username)
                    apply()
                }

                Toast.makeText(activity, "Token " + user.token, Toast.LENGTH_LONG).show()
                courseViewModel.getCourses(user.username, user.token)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(activity, "Token failure " + user.error, Toast.LENGTH_LONG).show()
            }
        }

        signInButton.setOnClickListener {
            val email = editEmail.text.toString()
            val clave = editPassword.text.toString()
            val usuario = email
            loginViewModel.signIn(email,clave,usuario).observe(getViewLifecycleOwner(), Observer { user -> saveTokenAndGoHome(user) })
        }

        signUpButton.setOnClickListener {
            val email = editEmail.text.toString()
            val clave = editPassword.text.toString()
            val usuario = email
            loginViewModel.signUp(email,clave,usuario).observe(getViewLifecycleOwner(), Observer { user -> saveTokenAndGoHome(user) })
        }
    }
}