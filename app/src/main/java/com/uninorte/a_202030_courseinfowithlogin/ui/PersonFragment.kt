package com.uninorte.a_202030_courseinfowithlogin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.PersonViewModel
import kotlinx.android.synthetic.main.fragment_person.*

/**
 * A simple [Fragment] subclass.
 */
class PersonFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personViewModel: PersonViewModel by activityViewModels()
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","").toString()
        val usuario = sharedPreferences.getString("usuario","").toString()

        val professor_id = requireArguments().getString("professor_id")
        if (professor_id != null) {
            personViewModel.getProfessor(usuario,token,professor_id)
        } else {
            val student_id = requireArguments().getString("student_id")
            if (student_id != null) {
                personViewModel.getStudent(usuario,token,student_id)
            }
        }

        personViewModel.getPersonData().observe(getViewLifecycleOwner(), Observer { person ->
            Log.d("MyOut", "Fragment person with " + person)
            personName.text = person.name
            personMail.text = person.email
            personCity.text = "City: " + person.city + ", " + person.country
            personPhone.text = "Phone: " + person.phone
        })
    }
}