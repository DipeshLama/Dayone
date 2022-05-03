package com.example.dayone.feature.shared.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dayone.R
import com.example.dayone.feature.shared.listener.ClickListener
import com.example.dayone.feature.shared.model.Student

class StudentViewHolder(view: View, listener : ClickListener) : RecyclerView.ViewHolder (view) {
    private val name = view.findViewById<TextView>(R.id.txvName)
    private val address = view.findViewById<TextView>(R.id.txvAddress)
    private val number  = view.findViewById<TextView>(R.id.txvPhoneNumber)
    private val delete = view.findViewById<ImageView>(R.id.imvDelete)

    init {
        delete.setOnClickListener {
            listener.onDelete(adapterPosition)
        }

        view.setOnClickListener{
            listener.onStudentClick(adapterPosition)
        }
    }

    fun bind (student : Student){
        name.text = student.name
        address.text = student.address
        number.text = student.phoneNumber
    }
}