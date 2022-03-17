package com.example.kiosk_gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.content.Intent




class Payment_Page : AppCompatActivity() {

    var pagestack : Int = 0
    var IsChoiceSelected : Boolean = false
    var whatselected_step1 : String = ""
    var whatselected_step2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paymentscreen)
        var nextbutton = findViewById<Button>(R.id.nextbutton)
        var previousbutton = findViewById<Button>(R.id.previousbutton)
        var pricetextview = findViewById<TextView>(R.id.final_price)
        var price = intent.getStringExtra("data")
        pricetextview.text = "₩${price}"

        nextbutton.setOnClickListener() {
            if (pagestack == 0) {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Step1_Fragment()).commit()
                pagestack += 1
            }
            else if (pagestack == 1) {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Step2_Fragment()).commit()
                pagestack += 1
            }
            else if (pagestack == 2) {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentarea, Payment_Fragment()).commit()
                nextbutton.isEnabled = false
                nextbutton.setBackgroundResource(R.drawable.disalbed_button)
                previousbutton.text = "처음으로"
                previousbutton.setOnClickListener(){
                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
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
}



