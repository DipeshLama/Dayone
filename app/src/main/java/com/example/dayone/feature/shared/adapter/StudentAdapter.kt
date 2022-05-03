package com.example.dayone.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dayone.R
import com.example.dayone.feature.shared.listener.ClickListener
import com.example.dayone.feature.shared.model.Student
import com.example.dayone.feature.shared.viewholder.StudentViewHolder

class StudentAdapter (var list : ArrayList<Student>, var listener : ClickListener) : RecyclerView.Adapter<StudentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list,parent,false)
        return StudentViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}