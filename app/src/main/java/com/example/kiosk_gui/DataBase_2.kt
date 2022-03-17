package com.example.kiosk_gui

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBase_2(context: Context? ,  name : String , factory : SQLiteDatabase.CursorFactory? , version : Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.disableWriteAheadLogging()
    }

    override fun onCreate(database: SQLiteDatabase?) {
        val history : String = "CREATE TABLE IF NOT EXISTS history(seq INTEGER PRIMARY KEY AUTOINCREMENT, image TEXT , name TEXT , num TEXT , price TEXT)"
        Log.d("안녀앙아녕앙낭냐얀ㅇ","ㄴ아럼아ㅣㄻ너ㅣㄹ너ㅣㅇㄻ나얼ㄹㅇ")
        database!!.execSQL(history)


    }

    override fun onUpgrade(database: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        val sql : String = "DROP TABLE account"
        database!!.execSQL(sql)
    }
}