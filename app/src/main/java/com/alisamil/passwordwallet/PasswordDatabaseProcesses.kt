package com.alisamil.passwordwallet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.lang.Exception

class PasswordDatabaseProcesses {




    fun passSave(context: Context?,userId:Int,username:String,password:String,img:ByteArray){
        val userId=userId.toLong()
        try {
            context?.let {
            val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
            val insert="INSERT INTO passwords(userID,username,password,gorsel) VALUES (?,?,?,?)"
            val statement=database.compileStatement(insert)
            statement.bindLong(1,userId)
            statement.bindString(2,username)
            statement.bindString(3,password)
            statement.bindBlob(4,img)
            statement.execute()
            }
        }catch (e:Exception){

            e.printStackTrace()

        }


    }

    fun passEdit(context: Context?,id: Int,username:String,password:String,gorsel:ByteArray){
        try {
            context?.let {
                val database= it.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val update="UPDATE passwords SET username=?, password=?, gorsel=? WHERE id=${id}"


                    val statement=database.compileStatement(update)
                    statement.bindString(1,username)
                    statement.bindString(2,password)
                    statement.bindBlob(3,gorsel)
                    statement.execute()



            }
        }catch (e:Exception){

            e.printStackTrace()

        }






    }


    fun passDelete(context: Context?,id:Int){

        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                database.execSQL("DELETE FROM passwords WHERE id=${id}")
            }
        }catch (e:Exception){

            e.printStackTrace()

        }


    }


    fun passGet(context: Context?,id:Int){
        val idArray=ArrayList<Int>()
        val userIdArray=ArrayList<Int>()
        val usernameArray=ArrayList<String>()
        val passwordArray=ArrayList<String>()
        val imageArray=ArrayList<Bitmap>()
        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE userID=id",null)
                val idIndex=cursor.getColumnIndex("id")
                val userIDIndex=cursor.getColumnIndex("userID")
                val username=cursor.getColumnIndex("username")
                val password=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    val idGet=cursor.getInt(idIndex)
                    idArray.add(idGet)
                    val userIdGet=cursor.getInt(userIDIndex)
                    userIdArray.add(userIdGet)
                    val usernameGet=cursor.getString(username)
                    usernameArray.add(usernameGet)
                    val passwordGet=cursor.getString(password)
                    passwordArray.add(passwordGet)
                    val gorselGet=cursor.getBlob(gorselIndex)
                    val bitmap=BitmapFactory.decodeByteArray(gorselGet,0,gorselGet.size)
                    imageArray.add(bitmap)

                }
                cursor.close()




            }
        }catch (e:Exception){

            e.printStackTrace()

        }



    }

    fun passUsernameGet(context: Context?,id:Int) : ArrayList<String>{
        val idArray=ArrayList<Int>()
        val userIdArray=ArrayList<Int>()
        val usernameArray=ArrayList<String>()
        val passwordArray=ArrayList<String>()
        val imageArray=ArrayList<Bitmap>()
        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE userID=${id}",null)
                val idIndex=cursor.getColumnIndex("id")
                val userIDIndex=cursor.getColumnIndex("userID")
                val username=cursor.getColumnIndex("username")
                val password=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    val idGet=cursor.getInt(idIndex)
                    idArray.add(idGet)
                    val userIdGet=cursor.getInt(userIDIndex)
                    userIdArray.add(userIdGet)
                    val usernameGet=cursor.getString(username)
                    usernameArray.add(usernameGet)
                    val passwordGet=cursor.getString(password)
                    passwordArray.add(passwordGet)
                    val gorselGet=cursor.getBlob(gorselIndex)
                    val bitmap=BitmapFactory.decodeByteArray(gorselGet,0,gorselGet.size)
                    imageArray.add(bitmap)

                }
                cursor.close()



            }
        }catch (e:Exception){

            e.printStackTrace()

        }
        return usernameArray


    }
    fun passPasswordGet(context: Context?,id:Int) : ArrayList<String>{
        val idArray=ArrayList<Int>()
        val userIdArray=ArrayList<Int>()
        val usernameArray=ArrayList<String>()
        val passwordArray=ArrayList<String>()
        val imageArray=ArrayList<Bitmap>()
        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE userID=${id}",null)
                val idIndex=cursor.getColumnIndex("id")
                val userIDIndex=cursor.getColumnIndex("userID")
                val username=cursor.getColumnIndex("username")
                val password=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    val idGet=cursor.getInt(idIndex)
                    idArray.add(idGet)
                    val userIdGet=cursor.getInt(userIDIndex)
                    userIdArray.add(userIdGet)
                    val usernameGet=cursor.getString(username)
                    usernameArray.add(usernameGet)
                    val passwordGet=cursor.getString(password)
                    passwordArray.add(passwordGet)
                    val gorselGet=cursor.getBlob(gorselIndex)
                    val bitmap=BitmapFactory.decodeByteArray(gorselGet,0,gorselGet.size)
                    imageArray.add(bitmap)

                }
                cursor.close()



            }
        }catch (e:Exception){

            e.printStackTrace()

        }
        return passwordArray


    }


    fun passImageGet(context: Context?,id:Int) : ArrayList<Bitmap>{
        val idArray=ArrayList<Int>()
        val userIdArray=ArrayList<Int>()
        val usernameArray=ArrayList<String>()
        val passwordArray=ArrayList<String>()
        val imageArray=ArrayList<Bitmap>()
        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE userID=${id}",null)
                val idIndex=cursor.getColumnIndex("id")
                val userIDIndex=cursor.getColumnIndex("userID")
                val username=cursor.getColumnIndex("username")
                val password=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    val idGet=cursor.getInt(idIndex)
                    idArray.add(idGet)
                    val userIdGet=cursor.getInt(userIDIndex)
                    userIdArray.add(userIdGet)
                    val usernameGet=cursor.getString(username)
                    usernameArray.add(usernameGet)
                    val passwordGet=cursor.getString(password)
                    passwordArray.add(passwordGet)
                    val gorselGet=cursor.getBlob(gorselIndex)
                    val bitmap=BitmapFactory.decodeByteArray(gorselGet,0,gorselGet.size)
                    imageArray.add(bitmap)

                }
                cursor.close()



            }
        }catch (e:Exception){

            e.printStackTrace()

        }
        return imageArray


    }
    fun passIdGet(context: Context?,id:Int) : ArrayList<Int>{
        val idArray=ArrayList<Int>()
        val userIdArray=ArrayList<Int>()
        val usernameArray=ArrayList<String>()
        val passwordArray=ArrayList<String>()
        val imageArray=ArrayList<Bitmap>()
        try {
            context?.let {
                val database= context.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE userID=${id}",null)
                val idIndex=cursor.getColumnIndex("id")
                val userIDIndex=cursor.getColumnIndex("userID")
                val username=cursor.getColumnIndex("username")
                val password=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    val idGet=cursor.getInt(idIndex)
                    idArray.add(idGet)
                    val userIdGet=cursor.getInt(userIDIndex)
                    userIdArray.add(userIdGet)
                    val usernameGet=cursor.getString(username)
                    usernameArray.add(usernameGet)
                    val passwordGet=cursor.getString(password)
                    passwordArray.add(passwordGet)
                    val gorselGet=cursor.getBlob(gorselIndex)
                    val bitmap=BitmapFactory.decodeByteArray(gorselGet,0,gorselGet.size)
                    imageArray.add(bitmap)

                }
                cursor.close()



            }
        }catch (e:Exception){

            e.printStackTrace()

        }
        return idArray


    }


}