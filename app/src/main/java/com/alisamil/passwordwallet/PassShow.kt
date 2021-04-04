package com.alisamil.passwordwallet

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_pass_show.*
import java.lang.Exception


class PassShow : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var image=PassShowArgs.fromBundle(it).Bitmap
            var name=PassShowArgs.fromBundle(it).username
            var pass=PassShowArgs.fromBundle(it).password
            var id=PassShowArgs.fromBundle(it).rawId
            var userId=PassShowArgs.fromBundle(it).UserID

            deleteImageView.setOnClickListener {

                context?.let { it1 -> deleteClick(it1,it,id,userId) }

            }

            gearImageView.setOnClickListener{
                val action=PassShowDirections.actionPassShowToPassEdit(id,userId)
                Navigation.findNavController(it).navigate(action)


            }


            passShowImageView.setImageBitmap(image)
            UserNameShowTextView.setText(name)
            PassShowTextView.setText(pass)

        }







    }

    fun deleteClick(context: Context,view: View,id:Int,userId:Int){
        try {
            val alerT=AlertDialog.Builder(context)
            alerT.setTitle("Delete")
            alerT.setMessage("Do you want to delete this pass?")
            alerT.setPositiveButton("No",DialogInterface.OnClickListener({dialog, which ->   }))
            alerT.setNegativeButton("Delete",DialogInterface.OnClickListener { dialog, which ->

                val database=PasswordDatabaseProcesses()
                database.passDelete(context,id)
                val action=PassShowDirections.actionPassShowToMainScreen(userId)
                Navigation.findNavController(view).navigate(action)


            })
            alerT.show()

        }catch (e:Exception){
            e.printStackTrace()

        }




    }


}