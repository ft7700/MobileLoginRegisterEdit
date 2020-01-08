package com.example.loginregister

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        handler = DatabaseHelper(this)


        btnSave.setOnClickListener(){
            updateMe()
            id.setText("")
            id_username.setText("")
            id_email.setText("")
            id_password.setText("")
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

        btnShowData.setOnClickListener(){
            readMe()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

        btnDelete.setOnClickListener(){
            deleteMe()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun updateMe(){
        val id = id.text.toString()
        val name = id_username.text.toString()
        val email = id_email.text.toString()
        val password = id_password.text.toString()
        val result = handler.updateUserData(id,name,email,password)
        if(result == true){
            Toast.makeText(this,"Data updated successfully", Toast.LENGTH_SHORT).show()
        }else
        {
            Toast.makeText(this,"Data updated failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun readMe(){
        val res =  handler.allData
        val buffer = StringBuffer()
        if(res !=null && res.count>0){
            while(res.moveToNext()){
                buffer.append("Record ID: " + res.getString(0)+ "\n")
                buffer.append("name: " + res.getString(1)+ "\n")
                buffer.append("email: " + res.getString(2)+ "\n")
                buffer.append("password: " + res.getString(3)+ "\n\n")
            }
            txt.text = buffer.toString()
            Toast.makeText(this,"Data retrieved successfully",Toast.LENGTH_SHORT).show()
        }
    }


    private fun deleteMe(){
        val id = id.text.toString()
        val result = handler.deleteUserData(id)

        Toast.makeText(this,"Profile has been successfully deleted",Toast.LENGTH_SHORT).show()
    }
}
