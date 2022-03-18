package com.example.kiosk_gui

import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder


class Payment_Page : AppCompatActivity() {

    var pagestack : Int = 0
    var IsChoiceSelected : Boolean = false
    var drinklist_array2 = mutableListOf<List<String>>()
    var drinklist_array = ArrayList<ArrayList<String>>()
    var whatselected_step1 : String = ""
    var whatselected_step2 : String = ""
    var isService = false
    lateinit var myService: Shopping_Basket_Service
    var db = DataBase(this,"user_info.db",null,1)
    var datacontrol = DataBase_Control()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as Shopping_Basket_Service.MyBinder
                myService = binder.getService()
                isService = true
                drinklist_array2 = myService.return_Shopping_Basket()
                Log.d("test3", drinklist_array2.toString())
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isService = false
            }
        }

        var myintent = Intent(this, Shopping_Basket_Service::class.java)
        bindService(myintent, connection, Context.BIND_AUTO_CREATE)

        setContentView(R.layout.paymentscreen)
        var nextbutton = findViewById<Button>(R.id.nextbutton)
        var previousbutton = findViewById<Button>(R.id.previousbutton)
        var pricetextview = findViewById<TextView>(R.id.final_price)
        var price = intent.getStringExtra("data")
        pricetextview.text = "₩${price}"

        nextbutton.setOnClickListener() {
            if (pagestack == 0) {
                Log.d("test3", drinklist_array2.toString())
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Step1_Fragment()).commit()
                pagestack += 1
            }
            else if (pagestack == 1) {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Step2_Fragment()).commit()
                pagestack += 1
            }
            else if (pagestack == 2) {

                var writeable = db.writableDatabase
                var readable = db.readableDatabase

                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Fragment()).commit()
                nextbutton.setText(R.string.history)

                for (i in 0..drinklist_array2.size-1){
                    datacontrol.createaccount(writeable,"history", listOf("image","name","num","price"),
                        listOf(drinklist_array2[i][0],drinklist_array2[i][1],drinklist_array2[i][2],drinklist_array2[i][7]))
                }

                drinklist_array = datacontrol.readaccount(readable,"history", listOf("asdf","asdf"),
                    listOf("asdf","asdf"))

                Log.d("sksmslahdfkefs",drinklist_array.toString())

                myService.cleaning_Basket()
                drinklist_array2 = myService.return_Shopping_Basket()
                Log.d("서비스 지움", drinklist_array2.toString())

                previousbutton.setText(R.string.first)
                previousbutton.setOnClickListener(){
                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }

                nextbutton.setOnClickListener(){
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, History_Fragment()).commit()
                    nextbutton.isEnabled = false
                    nextbutton.text = "주문내역 입니다."
                    nextbutton.setBackgroundResource(R.drawable.disalbed_button)
                }
            }
        }


        previousbutton.setOnClickListener(){

            if (pagestack == 0) {
                this.finish()
            }

            else if (pagestack == 1) {
                this.finish()
                pagestack -= 1
            }

            else if (pagestack == 2) {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Step1_Fragment()).commit()
                pagestack -= 1
            }
        }
    }

    fun return_Array() : ArrayList<ArrayList<String>> {
        return drinklist_array
    }
}



