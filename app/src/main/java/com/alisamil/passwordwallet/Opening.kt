package com.alisamil.passwordwallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlin.concurrent.thread


class Opening : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opening, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val splash = object : Thread() {
            override fun run() {
                try {

                    Thread.sleep(3000)
                    val action=OpeningDirections.actionOpeningToLogin()
                    Navigation.findNavController(view).navigate(action)

                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        splash.start()


    }


}