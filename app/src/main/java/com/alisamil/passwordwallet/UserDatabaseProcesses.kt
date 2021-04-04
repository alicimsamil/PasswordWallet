package com.alisamil.passwordwallet

import android.content.Context
import kotlinx.coroutines.processNextEventInCurrentThread
import java.lang.Exception

class UserDatabaseProcesses {
    fun userAdd(context:Context,name:String,password:String) : Boolean{
        context?.let {
            val donus=userSearch(it,name)
            if(name!=donus){
                try {
                    val database=it.openOrCreateDatabase("PasswordWallet",Context.MODE_PRIVATE,null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username VARCHAR, password VARCHAR)")
                    val insert="INSERT INTO users(username,password) VALUES (?,?)"
                    val statement=database.compileStatement(insert)
                    statement.bindString(1,name)
                    statement.bindString(2,password)
                    statement.execute()
                    return true

                }catch (e:Exception){
                    e.printStackTrace()
                    return false
                }
            }else{
                return false

            }


        }

    }

    fun userSearch(context: Context,name: String) : String{

        context?.let {
            try {
                var output="False"
                val database=it.openOrCreateDatabase("PasswordWallet",Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username VARCHAR, password VARCHAR)")
                val cursor = database.rawQuery("SELECT username FROM users WHERE username='${name}'",null)
                val usernameIndex = cursor.getColumnIndex("username")
                while (cursor.moveToNext()){

                    output=cursor.getString(usernameIndex)
                }
                cursor.close()
                return output


            }catch (e:Exception){
                e.printStackTrace()
                return "Error"
            }

        }


    }

    fun userLogin(username:String,password:String,context: Context) : Int?{
        var idOutput : Int? = null
        try {
            context?.let {

                val database=it.openOrCreateDatabase("PasswordWallet",Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username VARCHAR, password VARCHAR)")
                val cursor = database.rawQuery("SELECT id FROM users WHERE username='${username}' AND password='${password}'",null)
                val idIndex = cursor.getColumnIndex("id")

                while (cursor.moveToNext()){

                    idOutput=cursor.getInt(idIndex)

                }
                cursor.close()
            }
        }catch (e:Exception){
            e.printStackTrace()

        }




            return idOutput
    }





}