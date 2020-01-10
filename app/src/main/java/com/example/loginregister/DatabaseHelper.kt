package com.example.loginregister

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



val dbname = "userdb"
val col_name="name"
val col_email="email"
val col_password="password"


    class DatabaseHelper(context: Context):SQLiteOpenHelper(context, dbname,null,1){
        override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("create table user(id integer primary key autoincrement," +
                    col_name + "varchar(30)"+
                    col_email + "varchar(100)"+
                    col_password + "varchar(20)")
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
         }

        val allData : Cursor
        get() {val db = this.writableDatabase
        return db.rawQuery("Select * from user",null)
        }

    fun insertUserData(name:String, email: String, password:String){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("password",password)


        db.insert("user",null,values)
        db.close()
    }


    fun updateUserData(id:String, name: String, email: String, password:String):Boolean{
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("password",password)

        val result =db.update("user",values,"id = ?", arrayOf(id))

        return if(result>0) {
            true
        }else {
            false
        }
    }


        fun deleteUserData(id:String): Int?{
            val db: SQLiteDatabase = writableDatabase
            val i = db.delete("user","id = ?", arrayOf(id))
            return i
        }

    fun userExist(email: String, password: String): Boolean{
        val db = writableDatabase
        val query = "select * from user where email='$email' and password='$password'"
        val cursor:Cursor = db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }

        cursor.close()
        return true
    }

    }