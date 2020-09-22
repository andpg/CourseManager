package com.uninorte.a_202030_courseinfowithlogin.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.User
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginViewModel: LoginViewModel by activityViewModels()
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        if (sharedPreferences.contains("usuario") and sharedPreferences.contains("token")) {
            val token = sharedPreferences.getString("token","")!!
            loginViewModel.checkToken(token).observe(getViewLifecycleOwner(), { isValid ->
                if (isValid) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    sharedPreferences.edit {
                        remove("token")
                        remove("usuario")
                        apply()
                    }
                }
            })
        }

        fun saveTokenAndGoHome(user: User) {
            if (user.token != "") {
                sharedPreferences.edit {
                    putString("token",user.token)
                    putString("usuario",user.username)
                    apply()
                }

                Toast.makeText(activity, "Token " + user.token, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {

                showAlert(user.code)
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

    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Alert")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}