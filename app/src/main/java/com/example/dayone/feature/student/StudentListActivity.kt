package com.example.dayone.feature.student

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dayone.R
import com.example.dayone.utils.Constants
import com.example.dayone.databinding.ActivityStudentListBinding
import com.example.dayone.databinding.AddUpdateDialogBinding
import com.example.dayone.feature.shared.adapter.StudentAdapter
import com.example.dayone.feature.shared.listener.ClickListener
import com.example.dayone.feature.shared.model.Student
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class StudentListActivity : MvpActivity<StudentView,StudentPresenter>(), StudentView, ClickListener {
    private lateinit var binding : ActivityStudentListBinding
    private lateinit var dialogBoxBinding : AddUpdateDialogBinding
    private lateinit var name : TextInputEditText
    private lateinit var address : TextInputEditText
    private lateinit var phoneNumber : TextInputEditText
    private var adapter : StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.getStudent()
    }

    override fun createPresenter() =  StudentPresenter()

    private fun showAddUpdateDialog(type : String, position: Int){
        val view = View.inflate(this, R.layout.add_update_dialog, null)
        dialogBoxBinding = AddUpdateDialogBinding.bind(view)
        name = dialogBoxBinding.edtName
        address = dialogBoxBinding.edtAddress
        phoneNumber = dialogBoxBinding.edtPhone
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(view)
        val originalDialog = alertDialogBuilder.create()
        originalDialog.window?.setDimAmount(0.7f)
        originalDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        if(type == Constants.update){
            presenter.getSingleStudent(position)
        }

        dialogBoxBinding.btnOk.setOnClickListener{
            if(!dialogBoxBinding.edtName.text?.isEmpty()!! && !dialogBoxBinding.edtAddress.text?.isEmpty()!! && !dialogBoxBinding.edtPhone.text?.isEmpty()!!){
                val inputName = name.text.toString()
                val inputAddress = address.text.toString()
                val inputNumber = phoneNumber.text.toString()
                if(type == Constants.add){
                    presenter.addStudent(Student(inputName,inputAddress,inputNumber))
                }else{
                    presenter.updateStudent(inputName, inputAddress, inputNumber,position)
                }
                originalDialog.dismiss()
            }else{
                Toast.makeText(this, Constants.fieldError, Toast.LENGTH_SHORT).show()
            }
        }

        dialogBoxBinding.btnCancel.setOnClickListener{
            originalDialog.dismiss()
        }
        originalDialog.setCanceledOnTouchOutside(true)
        originalDialog.show()
        originalDialog.window?.setGravity(Gravity.CENTER)
    }

    override fun getStudent(list: ArrayList<Student>) {
        populateRecyclerView(list)
    }

    private fun populateRecyclerView (student : ArrayList<Student>){
        adapter = StudentAdapter(student, this)
        binding.ryvStudentList.adapter = adapter
    }

    override fun getSingleStudent(student: Student) {
        name.setText(student.name)
        address.setText(student.address)
        phoneNumber.setText(student.phoneNumber)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyChange() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDelete(position: Int) {
        presenter.deleteStudent(position)
    }

    override fun onStudentClick(position: Int) {
        showAddUpdateDialog(Constants.update,position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add ->{
                showAddUpdateDialog(Constants.add, -1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}