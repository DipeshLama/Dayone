package com.example.dayone.feature.student

import com.example.dayone.feature.shared.model.Student
import com.example.dayone.feature.shared.repository.StudentRepository

class StudentInteracter {
    private val studentRepository = StudentRepository()
    fun addStudent(student : Student) = studentRepository.addStudent(student)
    fun getStudent () = studentRepository.getStudent()
    fun getSingleStudent (position: Int) = studentRepository.getSingleStudent(position)
    fun updateStudent (name : String, address : String , phone : String, position: Int) = studentRepository.updateStudent(name, address,phone, position)
    fun deleteStudent (position : Int) = studentRepository.deleteStudent(position)
}