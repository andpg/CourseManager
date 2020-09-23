package com.uninorte.a_202030_courseinfowithlogin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.viewmodel.CourseMembersViewModel
import kotlinx.android.synthetic.main.fragment_course.*

/**
 * A simple [Fragment] subclass.
 */
class CourseFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseViewModel: CourseMembersViewModel by activityViewModels()
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","").toString()
        val usuario = sharedPreferences.getString("usuario","").toString()
        val course_id = requireArguments().getString("course_id")

        courseViewModel.getCourseMembers(usuario, token, course_id!!)
        courseViewModel.getCourseMembersData().observe(getViewLifecycleOwner(), Observer { course ->
            Log.d("MyOut", "Fragment course with " + course)
            courseName.text = course.name
            profName.text = course.professor.name
            profMail.text = course.professor.email

            profButton.setOnClickListener {
                val bundle = bundleOf("professor_id" to course.professor.id)
                view.findNavController().navigate(R.id.action_courseFragment_to_personFragment, bundle)
            }
        })
    }
}