package com.uninorte.a_202030_courseinfowithlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        fun bind(post: Course){
            with (itemView) {
                course_name.text = post.name
                professor_name.text = "Professor: " + post.professor
                student_number.text = "Number of students: " + post.students
            }
        }
    }
}