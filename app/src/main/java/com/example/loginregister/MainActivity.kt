package com.example.loginregister

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choice.*
//import com.example.loginregister.DatabaseHelper.Companion.dbname
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.choice
import kotlinx.android.synthetic.main.activity_main.login_layout
import kotlinx.android.synthetic.main.activity_main.registration_layout
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_userchoice.*
import kotlinx.android.synthetic.main.admin_login.*
import kotlinx.android.synthetic.main.admin_registration.*
import kotlinx.android.synthetic.main.user_login.*
import kotlinx.android.synthetic.main.user_registration.*

class MainActivity : AppCompatActivity() {


    lateinit var handler: DatabaseHelper




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = DatabaseHelper(this)


        showHome()

        admin.setOnClickListener() {
            showChoice()
        }

        user.setOnClickListener() {
            showUserChoice()
        }

        login.setOnClickListener() {
            showLogin()
        }

        registration.setOnClickListener() {
            showRegistration()
        }

        userlogin.setOnClickListener() {
            showUserLogin()
        }

        userregister.setOnClickListener() {
            showUserRegistration()
        }

        save.setOnClickListener() {
            if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString())
                || TextUtils.isEmpty(password_register.getText().toString())
            ) {
                Toast.makeText(this, "Registered failed, please enter all the required fields", Toast.LENGTH_SHORT).show()
            } else {
                handler.insertUserData(
                    name.text.toString(),
                    email.text.toString(),
                    password_register.text.toString()
                )
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            }
            showChoice()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

        //admin side
        login_button.setOnClickListener() {
            btnUpdate.visibility = View.GONE
            if (handler.userExist(login_email.text.toString(), login_password.text.toString())) {
                var a: String = login_email.text.toString()
                var admin1: String = "abc123@gmail.com"
                var admin2: String = "abc888@gmail.com"
                var admin3: String = "abc5566@gmail.com"
                if (a == admin1 || a == admin2 || a == admin3) {
                    Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show()
                    btnUpdate.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this,"Login failed, user cannot login here", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Login failed, username or password is incorrect!", Toast.LENGTH_SHORT).show()
            }
                showChoice()
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }



        //user side
        user_login_button.setOnClickListener() {

            if (handler.userExist(user_login_email.text.toString(), user_login_password.text.toString())) {
                var a: String = user_login_email.text.toString()
                var admin1: String = "abc123@gmail.com"
                var admin2: String = "abc888@gmail.com"
                var admin3: String = "abc5566@gmail.com"
                if (a == admin1 || a == admin2 || a == admin3) {
                    Toast.makeText(this, "Login failed, admin cannot login here", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Login Successfully!", Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(this,"Login failed, username or password is incorrect!", Toast.LENGTH_SHORT).show()
            showUserChoice()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }


            user_save.setOnClickListener() {
                if (TextUtils.isEmpty(user_name.getText().toString()) || TextUtils.isEmpty(user_email.getText().toString())
                    || TextUtils.isEmpty(user_password_register.getText().toString())
                ) {
                    Toast.makeText(this, "Registered failed, please enter all the required fields", Toast.LENGTH_SHORT).show()
                } else {
                    handler.insertUserData(
                        user_name.text.toString(),
                        user_email.text.toString(),
                        user_password_register.text.toString()
                    )
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                }
                showUserChoice()
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }


            btnUpdate.setOnClickListener() {
                val intent = Intent(this, UpdateActivity::class.java)
                startActivity(intent)
            }

            choiceback.setOnClickListener(){
                showUserChoice()
            }

            choiceback2.setOnClickListener(){
                showUserChoice()
             }

            choiceback3.setOnClickListener(){
            showHome()
            }

            choiceback4.setOnClickListener(){
            showHome()
            }

            choiceback5.setOnClickListener(){
                showChoice()
             }

            choiceback6.setOnClickListener(){
                showChoice()
            }


        }

        fun showRegistration() {
            registration_layout.visibility = View.VISIBLE
            login_layout.visibility = View.GONE
            home1.visibility = View.GONE
            choice.visibility = View.GONE
            userchoice.visibility = View.GONE

            name.setText("")
            email.setText("")
            password_register.setText("")

        }

        fun showUserRegistration() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.GONE
            home1.visibility = View.GONE
            choice.visibility = View.GONE
            userchoice.visibility = View.GONE
            user_registration_layout.visibility = View.VISIBLE

            user_name.setText("")
            user_email.setText("")
            user_password_register.setText("")

        }

        fun showLogin() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.VISIBLE
            home1.visibility = View.GONE
            choice.visibility = View.GONE
            userchoice.visibility = View.GONE

            login_email.setText("")
            login_password.setText("")
        }

        fun showUserLogin() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.GONE
            home1.visibility = View.GONE
            choice.visibility = View.GONE
            userchoice.visibility = View.GONE
            user_login_layout.visibility = View.VISIBLE

            user_login_email.setText("")
            user_login_password.setText("")
        }

        fun showHome() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.GONE
            home1.visibility = View.VISIBLE
            user_registration_layout.visibility = View.GONE
            choice.visibility = View.GONE
            user_login_layout.visibility = View.GONE
            userchoice.visibility = View.GONE
            user_registration_layout.visibility = View.GONE
        }

        fun showChoice() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.GONE
            home1.visibility = View.GONE
            choice.visibility = View.VISIBLE
            userchoice.visibility = View.GONE
            user_login_layout.visibility = View.GONE
            user_registration_layout.visibility = View.GONE
        }

        fun showUserChoice() {
            registration_layout.visibility = View.GONE
            login_layout.visibility = View.GONE
            home1.visibility = View.GONE
            user_login_layout.visibility = View.GONE
            userchoice.visibility = View.VISIBLE
            user_registration_layout.visibility = View.GONE
        }

    }


