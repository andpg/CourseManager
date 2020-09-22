package com.uninorte.a_202030_courseinfowithlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import kotlinx.android.synthetic.main.layout_list_item_courses.view.*

class CourseAdapter(var courses: MutableList<Course>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_courses, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CourseViewHolder ->{
                holder.bind(courses.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }



    class CourseViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        fun bind(course: Course){
            with (itemView) {
                course_name.text = course.name
                professor_name.text = "Professor: " + course.professor
                student_number.text = "Number of students: " + course.students

                buttonMembers.setOnClickListener {
                    val bundle = bundleOf("course_id" to course.id)
                    findNavController().navigate(R.id.action_homeFragment_to_courseFragment, bundle)
                }
            }
        }
    }
}