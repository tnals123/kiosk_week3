package com.example.kiosk_gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Login_Page_Fragment : Fragment() {


    override fun onCreateView(inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?):View {
        //규칙임
        var myview = inflater.inflate(R.layout.loginfragment, container, false)

        var resisterbutton = myview.findViewById<Button>(R.id.resister)
        resisterbutton.setOnClickListener(){
            (activity as Login_Page).replace_Fragment()
        }

        var loginbutton = myview.findViewById<Button>(R.id.login)
        // 데이터베이스 클래스 객체

        var id = myview.findViewById<EditText>(R.id.idedit).text.toString()
        var pw = myview.findViewById<EditText>(R.id.pwedit).text.toString()

        loginbutton.setOnClickListener(){
            var id = myview.findViewById<EditText>(R.id.idedit).text.toString()
            var pw = myview.findViewById<EditText>(R.id.pwedit).text.toString()
            (activity as Login_Page).Login(id,pw)
        }

        return myview
    }






}