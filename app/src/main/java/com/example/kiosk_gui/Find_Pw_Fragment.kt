package com.example.kiosk_gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class Find_Pw_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //규칙임
        var myview = inflater.inflate(R.layout.findpw_fragment, container, false)

        var idbutton = myview.findViewById<Button>(R.id.id)
        idbutton.setOnClickListener(){
            (activity as Login_Page).replace_Fragment_To_Id_Find()
        }
        var okbutton = myview.findViewById<Button>(R.id.changeid)
        var backbutton = myview.findViewById<Button>(R.id.goback)
        var id = myview.findViewById<EditText>(R.id.findidid)

        backbutton.setOnClickListener(){
            (activity as Login_Page).replace_Fragment_To_Login()
        }

        okbutton.setOnClickListener(){
            if (id.text.toString() == ""){
                Toast.makeText(activity, R.string.resister_failed , Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as Login_Page).find_Pw(id.text.toString())
            }
        }

        return myview
    }
}