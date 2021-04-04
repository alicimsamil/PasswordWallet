package com.alisamil.passwordwallet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.lang.Exception


class SignUp : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginTextView.setOnClickListener {
            login(it)

        }
        button.setOnClickListener {
            val name=userEditText.text.toString()
            val password=passwordEditText.text.toString()
            val passlength=password.length
            context?.let {
                if (name!="" && password!=""){
                    if ( passlength > 5 ) {
                        val save=UserDatabaseProcesses()
                        val sendSave=save.userAdd(it,name,password)
                        if(sendSave==true){

                            val action = SignUpDirections.actionSignUpToLogin()
                            Navigation.findNavController(view).navigate(action)



                        }else{
                            Toast.makeText(it,"Could not add user!",Toast.LENGTH_LONG).show()

                        }
                    }else{

                        Toast.makeText(it,"Your password must contain at least six character.!",Toast.LENGTH_LONG).show()

                    }


                }else{
                    Toast.makeText(it,"Please fill all blank!",Toast.LENGTH_LONG).show()


                }



            }



        }


    }

    fun login(view: View) {
        val action = SignUpDirections.actionSignUpToLogin()
        Navigation.findNavController(view).navigate(action)

    }
}