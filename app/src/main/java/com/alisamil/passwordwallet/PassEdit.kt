package com.alisamil.passwordwallet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_pass_edit.*
import kotlinx.android.synthetic.main.fragment_pass_edit.btnPopup
import kotlinx.android.synthetic.main.fragment_pass_edit.passSavePassword
import kotlinx.android.synthetic.main.fragment_pass_edit.passSaveUsername
import kotlinx.android.synthetic.main.fragment_pass_edit.saveButton
import kotlinx.android.synthetic.main.fragment_pass_save.*
import java.io.ByteArrayOutputStream
import java.lang.Exception


class PassEdit : Fragment() {
    lateinit var newUsername:String
    lateinit var newpassword:String
    lateinit var newImage:ByteArray
    lateinit var bitmap:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id=PassEditArgs.fromBundle(it).id
            context?.let { val database= it.openOrCreateDatabase("PasswordDatabase", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS passwords (id INTEGER PRIMARY KEY,userID INTEGER,username VARCHAR,password VARCHAR,gorsel BLOB)")
                val cursor=database.rawQuery("SELECT * FROM passwords WHERE id=${id}",null)
                val usernameIndex=cursor.getColumnIndex("username")
                val passwordIndex=cursor.getColumnIndex("password")
                val gorselIndex=cursor.getColumnIndex("gorsel")

                while (cursor.moveToNext()){

                    newUsername=cursor.getString(usernameIndex)
                    newpassword=cursor.getString(passwordIndex)
                    newImage=cursor.getBlob(gorselIndex)
                    bitmap=BitmapFactory.decodeByteArray(newImage,0,newImage.size)
                    btnPopup.setImageBitmap(bitmap)
                    passSaveUsername.setText(newUsername)
                    passSavePassword.setText(newpassword)


                }
                cursor.close()

            }




        }


        btnPopup.setOnClickListener {
            popUpButton(it)
        }
        saveButton.setOnClickListener {
            saveButoon(it)

        }



    }


    fun saveButoon(view:View){
        val bos= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos)
        val img=bos.toByteArray()
        newUsername=passSaveUsername.text.toString()
        newpassword=passSavePassword.text.toString()
        arguments?.let {
            PassEditArgs.fromBundle(it).userId
            PasswordDatabaseProcesses().passEdit(context,PassEditArgs.fromBundle(it).id,newUsername,newpassword,img)
            val action=PassEditDirections.actionPassEditToMainScreen(PassEditArgs.fromBundle(it).userId)
            Navigation.findNavController(view).navigate(action)



        }


    }

    fun popUpButton(view:View){
        context?.let {

            val popup= PopupMenu(it,view)
            popup.menuInflater.inflate(R.menu.save_popup_menu,popup.menu)

            try {
                val fieldMPopup= PopupMenu::class.java.getDeclaredField("mPopup")
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
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.instagram)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.twitter_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.twitter)
                    btnPopup.setImageBitmap(BitmapFactory.decodeResource(it.resources,R.drawable.twitter))
                }
                else if(id==R.id.facebook_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.facebook)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.youtube_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.youtube)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.whatsapp_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.whatsapp)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.tumblr_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.tumblr)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.spotify_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.spotify)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.snapchat_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.snapchat)
                    btnPopup.setImageBitmap(bitmap)
                }

                else if(id==R.id.outlook_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.outlook)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.likedin_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.linkedin)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.gmail_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.gmail)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.discord_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.discord)
                    btnPopup.setImageBitmap(bitmap)
                }
                else if(id==R.id.unknown_menu){
                    bitmap=BitmapFactory.decodeResource(it.resources,R.drawable.unknownapp)
                    btnPopup.setImageBitmap(bitmap)
                }
                false
            }



            popup.show()
        }



    }

}