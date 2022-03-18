package com.example.kiosk_gui

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBase(context: Context? ,  name : String , factory : SQLiteDatabase.CursorFactory? , version : Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        val sql : String = "CREATE TABLE IF NOT EXISTS userinfo(seq INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT , pw TEXT , name TEXT , phonenumber TEXT)"
        val asmode : String = "CREATE TABLE IF NOT EXISTS setting(seq INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 ,dark_mode TEXT DEFAULT 'basic')"
        val history : String = "CREATE TABLE IF NOT EXISTS history(seq INTEGER PRIMARY KEY AUTOINCREMENT, image TEXT , name TEXT , num TEXT , price TEXT)"

        database!!.execSQL(sql)
        database.execSQL(asmode)
        database.execSQL(history)

    }

    override fun onUpgrade(database: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        val sql : String = "DROP TABLE account"
        database!!.execSQL(sql)
    }
}