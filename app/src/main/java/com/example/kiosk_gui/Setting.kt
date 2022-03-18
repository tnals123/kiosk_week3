package com.example.kiosk_gui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.kiosk.Class.ThemeManager
import org.w3c.dom.Text
import java.util.*

class Setting : AppCompatActivity() {

    var db_2 = DataBase_2(this,"user_setting_db",null,1)
    var datacontrol = DataBase_Control()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        var langusesetting = findViewById<TextView>(R.id.languagesetting)
        var colorsetting = findViewById<TextView>(R.id.colorsetting)
        var koreanbutton = findViewById<RadioButton>(R.id.korean)
        var englishbutton = findViewById<RadioButton>(R.id.english)
        var basicbutton = findViewById<RadioButton>(R.id.basic)
        var darkbutton = findViewById<RadioButton>(R.id.dark)

        basicbutton.setOnClickListener(){
            var asdf = db_2.writableDatabase
            var zxcv = db_2.readableDatabase
            ThemeManager.applyTheme(ThemeManager.ThemeMode.DEFAULT)
        }
        darkbutton.setOnClickListener(){
            var asdf = db_2.writableDatabase
            var zxcv = db_2.readableDatabase
            ThemeManager.applyTheme(ThemeManager.ThemeMode.DARK)
        }

        koreanbutton.setOnClickListener(){
            setLanguage("ko")
            langusesetting.text = "언어 설정"
            koreanbutton.text = "한국어(Korean)"
            englishbutton.text = "영어(English)"
        }

        englishbutton.setOnClickListener(){
            setLanguage("en")
            langusesetting.text = "Language Setting"
            koreanbutton.text = "Korean"
            englishbutton.text = "English"
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
        super.onBackPressed()
    }

    fun setLanguage(lang:String) {
        val locale = Locale(lang)
        val config = resources.configuration

        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config,resources.displayMetrics)
    }
    fun setColor(color:String) {
        val locale = Locale(color)
        val config = resources.configuration

        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config,resources.displayMetrics)
    }
}