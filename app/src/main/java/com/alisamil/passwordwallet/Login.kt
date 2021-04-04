package com.alisamil.passwordwallet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*



class Login : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val user=userEditText.text.toString()
            val pass=passwordEditText.text.toString()
            context?.let {
                val query=UserDatabaseProcesses()
                val lookQuery=query.userLogin(user,pass,it)



                if(lookQuery!=null){
                    val sharedPreferences=it.getSharedPreferences("id", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putInt("user",lookQuery).apply()
                    val action=LoginDirections.actionLoginToMainScreen(lookQuery)
                    Navigation.findNavController(view).navigate(action)

                }else{
                    Toast.makeText(it,"Your username or password is incorrect.",Toast.LENGTH_LONG).show()

                }


            }


        }

        signUpTextView.setOnClickListener {
            signup(it)

        }

    }
    fun signup(view: View){
        val action = LoginDirections.actionLoginToSignUp()
        Navigation.findNavController(view).navigate(action)

    }


}