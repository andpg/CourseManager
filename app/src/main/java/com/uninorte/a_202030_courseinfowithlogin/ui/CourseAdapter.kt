package com.uninorte.a_202030_courseinfowithlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import kotlinx.android.synthetic.main.layout_list_item_courses.view.*

class CourseAdapter(private var courses: ArrayList<Course>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){



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
    ): RecyclerView.ViewHolder(itemView){
        val course_name = itemView.course_name
        val professor_name = itemView.professor_name
        val number_of_students = itemView.student_number

        fun bind(post: Course){
            course_name.setText(post.name)
            professor_name.setText(post.professor)
        }
    }
}