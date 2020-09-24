package com.uninorte.a_202030_courseinfowithlogin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uninorte.a_202030_courseinfowithlogin.R
import com.uninorte.a_202030_courseinfowithlogin.model.Student
import kotlinx.android.synthetic.main.layout_list_item_students.view.*

class StudentAdapter(var students: MutableList<Student>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_students, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CourseViewHolder ->{
                holder.bind(students.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }



    class CourseViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        fun bind(student: Student){
            with (itemView) {
                student_name.text = student.name
                student_mail.text = student.email
                setOnClickListener {
                    val bundle = bundleOf("student_id" to student.id)
                    findNavController().navigate(R.id.action_courseFragment_to_personFragment, bundle)
                }
            }
        }
    }
}