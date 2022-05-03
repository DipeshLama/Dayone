package com.example.dayone.feature.student

import com.example.dayone.feature.shared.model.Student
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class StudentPresenter : MvpBasePresenter<StudentView>() {
    private var studentInteracter :StudentInteracter? = null


    override fun attachView(view: StudentView) {
        super.attachView(view)
        studentInteracter = StudentInteracter()
    }

    override fun detachView() {
        studentInteracter =null
        super.detachView()
    }

    fun addStudent (student : Student){
        ifViewAttached { view ->
            studentInteracter?.addStudent(student)
            view.notifyChange()
        }
    }

    fun getStudent (){
        ifViewAttached { view ->
            view.getStudent(studentInteracter?.getStudent()!!)
        }
    }
    fun getSingleStudent (position: Int){
        ifViewAttached { view ->
           val student =  studentInteracter?.getSingleStudent(position)
            view.getSingleStudent(student!!)
        }
    }

    fun updateStudent(name : String, address : String , phone : String, position: Int){
        ifViewAttached { view ->
            studentInteracter?.updateStudent(name,address,phone,position)
            view.notifyChange()
        }
    }

    fun deleteStudent (position : Int){
        ifViewAttached { view ->
            studentInteracter?.deleteStudent(position)
            view.notifyChange()
        }
    }
}