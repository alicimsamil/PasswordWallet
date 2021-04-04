package com.alisamil.passwordwallet


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu

import kotlinx.android.synthetic.main.fragment_pass_save.*
import java.util.zip.Inflater
import android.view.ContextMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import java.io.ByteArrayOutputStream
import java.lang.Exception


class PassSave : Fragment() {
    lateinit var icon: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_save, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            icon=BitmapFactory.decodeResource(it.resources,R.drawable.unknownapp)
        }


        btnPopup.setOnClickListener {
            popUpButton(it)

        }




        saveButton.setOnClickListener {
            saveButoon(it)

        }







    }

    fun saveButoon(view: View){
        val username=passSaveUsername.text.toString()
        val password=passSavePassword.text.toString()
        val bos=ByteArrayOutputStream()
        icon.compress(Bitmap.CompressFormat.PNG,100,bos)
        val img=bos.toByteArray()



        arguments?.let {
            var gelenBilgi=PassSaveArgs.fromBundle(it).UserID
            PasswordDatabaseProcesses().passSave(context,gelenBilgi,username,password,img)
            val action=PassSaveDirections.actionPassSaveToMainScreen(gelenBilgi)
            Navigation.findNavController(view).navigate(action)
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun popUpButton(view:View){
        context?.let {

            val popup=PopupMenu(it,view)
            popup.menuInflater.inflate(R.menu.save_popup_menu,popup.menu)

            try {
                val fieldMPopup=PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible=true
                val mPopup=fieldMPopup.get(popup)
                mPopup.javaClass
                        .getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                        .invoke(mPopup,true)


            }catch (e : Exception){

                e.printStackTrace()
            }




            popup.setOnMenuItemClickListener { menuItem ->
                val  id = menuItem.itemId
                if (id==R.id.instagram_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.instagram)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.twitter_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.twitter)
                    btnPopup.setImageBitmap(BitmapFactory.decodeResource(it.resources,R.drawable.twitter))
                }
                else if(id==R.id.facebook_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.facebook)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.youtube_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.youtube)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.whatsapp_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.whatsapp)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.tumblr_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.tumblr)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.spotify_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.spotify)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.snapchat_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.snapchat)
                    btnPopup.setImageBitmap(icon)
                }

                else if(id==R.id.outlook_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.outlook)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.likedin_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.linkedin)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.gmail_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.gmail)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.discord_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.discord)
                    btnPopup.setImageBitmap(icon)
                }
                else if(id==R.id.unknown_menu){
                    icon=BitmapFactory.decodeResource(it.resources,R.drawable.unknownapp)
                    btnPopup.setImageBitmap(icon)
                }
                false
            }



            popup.show()
        }



    }





}