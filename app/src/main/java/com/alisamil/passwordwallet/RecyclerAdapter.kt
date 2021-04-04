package com.alisamil.passwordwallet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_shape.view.*
import java.util.zip.Inflater

class RecyclerAdapter(val userName:ArrayList<String>,val pass:ArrayList<String>,val socialImages : ArrayList<Bitmap>,val context: Context,val id:Int,val userId:ArrayList<Int>) : RecyclerView.Adapter<RecyclerAdapter.PasswordVH>() {

    class PasswordVH(itemView:View) : RecyclerView.ViewHolder(itemView){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_shape,parent,false)
        return PasswordVH(itemView)
    }

    override fun onBindViewHolder(holder: PasswordVH, position: Int) {


            if (socialImages.size-1!=position) {
                val a = holder.itemView.recyclerViewImageButton
                a.setImageBitmap(socialImages.get(position))
                a.setOnClickListener {
                    val action = MainScreenDirections.actionMainScreenToPassShow(socialImages.get(position),userName.get(position),pass.get(position),userId.get(position),id)
                    Navigation.findNavController(it).navigate(action)

                }

            }

            else {

                val x= holder.itemView.recyclerViewImageButton
                x.setImageBitmap(socialImages.get(position))
                x.setOnClickListener {

                    val action = MainScreenDirections.actionMainScreenToPassSave(id)
                    Navigation.findNavController(it).navigate(action)

                }




            }







    }

    override fun getItemCount(): Int {
        return socialImages.size
    }


}