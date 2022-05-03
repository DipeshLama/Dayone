package com.example.dayone.feature.shared.repository

import com.example.dayone.feature.shared.model.Student

class StudentRepository {
    val studentList = ArrayList<Student>()

    fun addStudent (student : Student){
        studentList.add(student)
    }

   fun getStudent () : ArrayList<Student> {
       return studentList
   }

    fun getSingleStudent (position: Int) : Student {
        return studentList[position]
    }

    fun updateStudent (name : String, address : String , phone : String , position: Int) {
        studentList[position].name = name
        studentList[position].address = address
        studentList[position].phoneNumber = phone
    }

    fun deleteStudent (position : Int){
        studentList.removeAt(position)
    }
}