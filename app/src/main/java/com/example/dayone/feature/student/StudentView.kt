package com.example.dayone.feature.student

import com.example.dayone.feature.shared.model.Student
import com.hannesdorfmann.mosby3.mvp.MvpView

interface StudentView : MvpView {
    fun getStudent (list : ArrayList<Student>)
    fun getSingleStudent (student : Student)
    fun notifyChange()
}