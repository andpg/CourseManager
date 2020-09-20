package com.uninorte.a_202030_courseinfowithlogin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.CourseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseViewModel: CourseViewModel by activityViewModels()
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val token: String = sharedPreferences.getString("token","").toString()
        val usuario: String = sharedPreferences.getString("usuario","").toString()

        courseViewModel.getCourses(usuario, token)

        courseViewModel.getCourseData().observe(getViewLifecycleOwner(), Observer { courses ->
            Log.d("MyOut", "Fragment users courses " + courses)
            val manager = LinearLayoutManager(context)
            viewCourses.apply {
                layoutManager = manager
                adapter = CourseAdapter(courses as ArrayList<Course>)
            }
        })

        buttonAddCourse.setOnClickListener {
           courseViewModel.addCourse(usuario,token)
        }

        signOut.setOnClickListener {
            sharedPreferences.edit {
                remove("token")
                remove("usuario")
                apply()
            }
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }
}