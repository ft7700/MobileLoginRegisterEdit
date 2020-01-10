package com.example.loginregister

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_show_user_profile.*

class ShowUserProfileActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user_profile)

        handler = DatabaseHelper(this)

        readMe()
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
            Toast.makeText(this,"Data retrieved successfully", Toast.LENGTH_SHORT).show()
        }
    }
}
