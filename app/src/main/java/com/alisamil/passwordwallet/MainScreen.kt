package com.alisamil.passwordwallet

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_shape.view.*

class MainScreen : Fragment() {
    val bundle=arguments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args= arguments?.let { MainScreenArgs.fromBundle(it).UserId }
        args?.let {


        context?.let {
            val callArray=Arrays()
            val bitmaps=callArray.imageAdd(it)
            val recyclerView : RecyclerView=view.findViewById(R.id.recyclerView)
            val gridLayout=GridLayoutManager(it,2)
            recyclerView.layoutManager=GridLayoutManager(it,2)

            val nameArray=callArray.userShow(it)
            val passArray=callArray.passShow(it)
            val idArray=callArray.returnId(it)


            val adapter=RecyclerAdapter(nameArray,passArray,bitmaps,it,args,idArray)
            recyclerView.adapter=adapter




        }
        }


    }


}