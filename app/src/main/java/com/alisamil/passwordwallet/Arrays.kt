package com.alisamil.passwordwallet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Arrays {

    fun imageAdd(context: Context) : ArrayList<Bitmap>{
        val res= context.resources
        val sharedPreferences=context.getSharedPreferences("id", Context.MODE_PRIVATE)
        val userId=sharedPreferences.getInt("user",0)
        val plusBitmap = BitmapFactory.decodeResource(res,R.drawable.plus)
        val call=PasswordDatabaseProcesses()
        val iconBitmap=call.passImageGet(context,userId)
        iconBitmap.add(plusBitmap)


        return iconBitmap

    }

    fun userShow(context: Context) : ArrayList<String> {
        val sharedPreferences=context.getSharedPreferences("id", Context.MODE_PRIVATE)
        val userId=sharedPreferences.getInt("user",0)
        val call=PasswordDatabaseProcesses()
        val userArray=call.passUsernameGet(context,userId)

        return userArray

    }
    fun passShow(context: Context) : ArrayList<String>{
        val sharedPreferences=context.getSharedPreferences("id", Context.MODE_PRIVATE)
        val userId=sharedPreferences.getInt("user",0)
        val call=PasswordDatabaseProcesses()
        val passwordArray=call.passPasswordGet(context,userId)

        return passwordArray

    }
    fun returnId(context: Context) : ArrayList<Int>{
        val sharedPreferences=context.getSharedPreferences("id", Context.MODE_PRIVATE)
        val userId=sharedPreferences.getInt("user",0)
        val call=PasswordDatabaseProcesses()
        val idArray=call.passIdGet(context,userId)

        return idArray

    }

}