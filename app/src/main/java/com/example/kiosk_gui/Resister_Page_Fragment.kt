package com.example.kiosk_gui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.stageus_week3.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Resister_Page_Fragment : Fragment() {

    var idok = ""
    var isbuttonClicked = ""

    fun login_Check_Api(id : String){


        var retrofit = RetrofitClient.initRetrofit()

        //api 와 통신!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //enqueue 는 callback 함수임 비동기를 처리해주는 함수
        var requestloginapi = retrofit.create(RetrofitClient.loginCheck::class.java)
        requestloginapi.getLoginCheck(id).enqueue(object : Callback<RetrofitClient.logincheckdata> {
            override fun onFailure(call: Call<RetrofitClient.logincheckdata>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<RetrofitClient.logincheckdata>,
                response: Response<RetrofitClient.logincheckdata>
            )  {
                Log.d("result", response.body()!!.message)
                Log.d("result", response.body()!!.success.toString())

                if (response.body()!!.success.toString() == "true") {
                    Toast.makeText(activity, R.string.resister_idok, Toast.LENGTH_SHORT).show()
                    idok = "true"
                    isbuttonClicked = "true"

                }
                else {
                    Toast.makeText(activity, R.string.resister_idcheckfailed_duplicated, Toast.LENGTH_SHORT).show()
                    idok = "false"
                    isbuttonClicked = "false"
                }

            }
        })

    }

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
            login_Check_Api(id.text.toString())
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
            else if (isbuttonClicked != "true"){
                Toast.makeText(activity, "아이디 중복체크를 안하셨어요", Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as Login_Page).resister_Api(id.text.toString(),pw.text.toString(),name.text.toString(), phone.text.toString())
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