package com.example.kiosk_gui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_page)


        var setting = findViewById<ImageView>(R.id.setting)
        setting.setOnClickListener(){
            var nextpage = Intent(this, Setting::class.java)
            startActivity(nextpage)
        }
        go_Next_Page()


    }
    fun setLanguage(lang:String) {
        var asdf = findViewById<TextView>(R.id.asdfasdf)
        val locale = Locale(lang) // locale을 설정하는 코드. lang의 언어로 Locale이 설정된다.
        val config = resources.configuration

        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config,resources.displayMetrics)
        asdf.setText(R.string.start_page_card)
    }

    fun go_Next_Page(){
        var clickbutton = findViewById<Button>(R.id.startbutton)
        clickbutton.setOnClickListener(){
            var nextpage = Intent(this,Menu_Screen_Page::class.java)
            startActivity(nextpage)
        }
    }
}