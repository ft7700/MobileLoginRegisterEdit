package com.example.loginregister

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update.*


class UpdateActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper
    lateinit var myDialog: Dialog
    lateinit var txt1: TextView

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
            val intent = Intent(this, ShowUserProfileActivity::class.java)
            startActivity(intent)

        }

        btnDelete.setOnClickListener(){
            deleteMe()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun updateMe(){

        var admin1: String = "1"
        var admin2: String = "2"
        var admin3: String = "3"
        if(id.text.toString() == admin1){
            id.setText("1")
            id_username.setText("abc123")
            id_email.setText("abc123@gmail.com")
            id_password.setText("abc123")

            showDialog()
        }else if(id.text.toString() == admin2){
            id.setText("2")
            id_username.setText("abc888")
            id_email.setText("abc888@gmail.com")
            id_password.setText("abc888")

            showDialog()
        }else if(id.text.toString() == admin3){
            id.setText("3")
            id_username.setText("abc5566")
            id_email.setText("abc5566@gmail.com")
            id_password.setText("abc5566")

            showDialog()
        }

        val id = id.text.toString()
        val name = id_username.text.toString()
        val email = id_email.text.toString()
        val password = id_password.text.toString()

        val result = handler.updateUserData(id,name,email,password)

        if(result == true){
            Toast.makeText(this,"Data updated successfully", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Data updated failed", Toast.LENGTH_SHORT).show()
        }

    }




    private fun deleteMe(){

            val id = id.text.toString()
            val result = handler.deleteUserData(id)

            Toast.makeText(this, "Profile has been successfully deleted", Toast.LENGTH_SHORT).show()

    }

    fun showDialog(){
        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.activity_dialog)
        myDialog.setTitle("Dialog Box")

        txt1=myDialog.findViewById<View>(R.id.btn_close) as TextView
        txt1.isEnabled= true
        txt1.setOnClickListener(){
            myDialog.cancel()
        }
        myDialog.show()
    }
}
