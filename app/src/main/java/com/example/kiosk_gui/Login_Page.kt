package com.example.kiosk_gui

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*

class Login_Page : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        var db = DataBase(this,"user_info.db",null,1)
        db.writableDatabase
        db.readableDatabase
        Log.d("낭ㄴㅁ르이ㅏㅁㄹㅇㄴㅁ읾ㄴㅇㄹㅇ",":ㅁㄴㅇㄻㄴ이ㅑㄻ나롬ㅇ니라ㅓㅂㄴㅇㄹㄴ")
        var datacontrol = DataBase_Control()
        var writeabledb = db.writableDatabase
        var readabledb = db.readableDatabase

        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage)
        supportFragmentManager.beginTransaction().replace(R.id.loginframelayout, Login_Page_Fragment()).commit()

    }

//    fun setLanguage() {
//        val config = resources.configuration // 내부 리소스가 설계된 구성을 지정한다.
//        val locale = Locale() // locale을 설정하는 코드. lang의 언어로 Locale이 설정된다.
//        Locale.setDefault(locale)
//        config.setLocale(locale)
//        resources.updateConfiguration(config,resources.displayMetrics)
//    }

    fun replace_Fragment(){
        supportFragmentManager.beginTransaction().replace(R.id.loginframelayout, Resister_Page_Fragment()).commit()
    }

    fun replace_Fragment_To_Login(){
        supportFragmentManager.beginTransaction().replace(R.id.loginframelayout, Login_Page_Fragment()).commit()
    }

    fun Login(id : String, pw : String){
        var db = DataBase(this,"user_info.db",null,1)
        var db2 = DataBase(this,"user_info.db",null,1)
        var datacontrol = DataBase_Control()

        var readabledb = db.readableDatabase // 데이터베이스 객체를 읽기 가능한 상황으로로
        var islogin = datacontrol.readaccount(readabledb, "userinfo", listOf("id","pw"), listOf(id,pw))
        var asdf = datacontrol.Check(readabledb, "userinfo")
        Log.d("로그인",asdf.toString())


        if(islogin.size == 0){
            Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
            }

        else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            }
        }

    fun resister_Id_Check(id : String , idedit : EditText){

        if (id.length <=4){
            Toast.makeText(this,  R.string.resister_idcheckfailed_tooshortorlong, Toast.LENGTH_SHORT).show()
            idedit.setText("")
            idedit.setHint(R.string.resister_id)
        }
        else if (id.length >=10){
            Toast.makeText(this, R.string.resister_idcheckfailed_tooshortorlong, Toast.LENGTH_SHORT).show()
            idedit.setText("")
            idedit.setHint(R.string.resister_id)
        }

        else {
            var db = DataBase(this,"user_info.db",null,1)
            var datacontrol = DataBase_Control()

            var readabledb = db.readableDatabase
            var islogin = datacontrol.readaccount(readabledb,"userinfo", listOf("id"), listOf(id))

            if (islogin.size == 0) {
                Toast.makeText(this, R.string.resister_idok, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this,  R.string.resister_idcheckfailed_duplicated, Toast.LENGTH_SHORT).show()
                idedit.setText("")
                idedit.setHint(R.string.resister_id)
            }
        }
    }

    fun resister_Pw_Check(pw : String , pwcheck : String, pwcheckedit : EditText){

        if (pw != pwcheck){
            pwcheckedit.setText("")
            pwcheckedit.setHintTextColor(ContextCompat.getColor(this, R.color.red))
            pwcheckedit.setHint(R.string.resister_pwcheckfailed_pwdifferent)
        }
    }

    fun resister_Pw_Check_Length(pw : String, pwedit : EditText){

        if (pw.length <=4){
            Toast.makeText(this, R.string.resister_pwcheckfailed_tooshortorlong, Toast.LENGTH_SHORT).show()
            pwedit.setText("")
            pwedit.setHint(R.string.resister_pw)
        }
        else if (pw.length >=10){
            Toast.makeText(this, R.string.resister_pwcheckfailed_tooshortorlong, Toast.LENGTH_SHORT).show()
            pwedit.setText("")
            pwedit.setHint(R.string.resister_pw)
        }
    }

    fun Resister(id : String , pw : String , name : String , phone : String){
        var db = DataBase(this,"user_info.db",null,1)
        var datacontrol = DataBase_Control()
        var readabledb = db.readableDatabase
        var writeabledb = db.writableDatabase
        datacontrol.createaccount(writeabledb,"userinfo", listOf("id","pw","name","phone"), listOf(id,pw,name,phone))
        datacontrol.Check(readabledb,"userinfo")
        Toast.makeText(this, R.string.resister_ok , Toast.LENGTH_SHORT).show()
        replace_Fragment_To_Login()
    }

}




