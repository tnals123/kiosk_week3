//package com.example.kiosk_gui
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//
//class Change_Pw_Fragment : Fragment() {
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        //규칙임
//        var myview = inflater.inflate(R.layout.changeidpwfragment, container, false)
//
//        var pwbutton = myview.findViewById<Button>(R.id.pwchan)
//        pwbutton.setOnClickListener(){
//            (activity as Login_Page).replace_Fragment_To_Pw_Find()
//        }
//        var okbutton = myview.findViewById<Button>(R.id.changebutton)
//        var backbutton = myview.findViewById<Button>(R.id.gobackback)
//        var id = myview.findViewById<EditText>(R.id.mychangeid)
//        var idcheck = myview.findViewById<EditText>(R.id.mychangeidcheck)
//
//        backbutton.setOnClickListener(){
//            (activity as Login_Page).replace_Fragment_To_Login()
//        }
//
//        okbutton.setOnClickListener(){
//            if (id.text.toString() == "" || idcheck.text.toString() == ""){
//                Toast.makeText(activity, R.string.resister_failed , Toast.LENGTH_SHORT).show()
//            }
//            else{
//                (activity as Login_Page).find_Id(name.text.toString(),phone.text.toString())
//            }
//        }
//
//        return myview
//    }
//}