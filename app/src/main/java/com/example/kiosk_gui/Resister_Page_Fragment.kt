package com.example.kiosk_gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class Resister_Page_Fragment : Fragment() {


    override fun onCreateView(inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?):View {
        //규칙임
        var myview = inflater.inflate(R.layout.resisterpage, container, false)

        var backbutton = myview.findViewById<Button>(R.id.back)
        backbutton.setOnClickListener(){
            (activity as Login_Page).replace_Fragment_To_Login()
        }
        var id = myview.findViewById<EditText>(R.id.idedit)
        var pw = myview.findViewById<EditText>(R.id.pwedit)
        var pwcheck = myview.findViewById<EditText>(R.id.pwcheck)
        var name = myview.findViewById<EditText>(R.id.name)
        var phone = myview.findViewById<EditText>(R.id.phone)
        var idcheckbutton = myview.findViewById<Button>(R.id.idcheck)

        idcheckbutton.setOnClickListener(){
            (activity as Login_Page).resister_Id_Check(id.text.toString(),id)
        }

        pwcheck.setOnClickListener(){
            (activity as Login_Page).resister_Pw_Check_Length(pw.text.toString(), pw)
        }

        name.setOnClickListener(){
            (activity as Login_Page).resister_Pw_Check(pw.text.toString(), pwcheck.text.toString(),pwcheck)
        }

        var signupbutton = myview.findViewById<Button>(R.id.resister)
        signupbutton.setOnClickListener(){
            if (id.text.toString() == "" || pw.text.toString() == "" || pwcheck.text.toString() == "" || name.text.toString() == "" ||
                    phone.text.toString() == ""){
                Toast.makeText(activity, R.string.resister_failed, Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as Login_Page).Resister(id.text.toString(),pw.text.toString(),name.text.toString(), phone.text.toString())
            }
        }

        return myview
    }




//        var signupbutton = findViewById<Button>(R.id.resister)
//        signupbutton.setOnClickListener(){
//            supportFragmentManager.beginTransaction().replace(R.id.loginframelayout, Resister_Page_Fragment()).commit()
////            datacontrol.createaccount(writeabledb,findViewById<EditText>(R.id.idedit).text.toString(),
////                findViewById<EditText>(R.id.pwedit).text.toString())
//
//        }




}