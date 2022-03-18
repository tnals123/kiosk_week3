package com.example.kiosk_gui

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import kotlin.io.path.createTempDirectory

class DataBase_Control {

    fun readaccount(database : SQLiteDatabase, name : String,  column : List<String>, value : List<String>) : ArrayList<ArrayList<String>> {

        var array = ArrayList<ArrayList<String>>()

        if (name == "history"){
            var mysql = "SELECT * FROM ${name}"
            var result : Cursor = database.rawQuery(mysql,null)

            while(result.moveToNext()) {
                val seq = result.getInt(0).toString()
                val id =  result.getString(1)
                val pw =  result.getString(2)
                val pw2 =  result.getString(3)
                val pw3 =  result.getString(4)
                val temarray = arrayListOf(seq,id,pw,pw2,pw3)
                array.add(temarray)
            }

            result.close() // 커서를 닫아주지 않으면 다음 작업이 실행되지 않음!!!!!!

            return array
        }

        else {

            for (i in 0..column.size - 1) {
                Log.d("asdfasdfasd", column[i])
            }
            var mysql = "SELECT * FROM ${name} WHERE ("
            for (i in 0..column.size - 1) {
                mysql += column[i] + " = '" + value[i] + "'"
                if (i != column.size - 1) {
                    mysql += " and "
                }
            }
            mysql += ")"
            var result : Cursor = database.rawQuery(mysql,null)

            while(result.moveToNext()) {
                val seq = result.getInt(0).toString()
                val id =  result.getString(1)
                val pw =  result.getString(2)
                result.moveToNext() // 다음 raw 로 넘어감, 리턴으로 받아오기
                val temarray = arrayListOf(seq,id,pw)
                array.add(temarray)
            }

            result.close() // 커서를 닫아주지 않으면 다음 작업이 실행되지 않음!!!!!!

            return array

        }


    }

    fun Check(database : SQLiteDatabase, name : String) : ArrayList<ArrayList<String>> {

        var array = ArrayList<ArrayList<String>>()
        var sql = "SELECT * FROM ${name}"
        var result : Cursor = database.rawQuery(sql,null)

        while(result.moveToNext()) {
            val seq = result.getInt(0).toString()
            val id =  result.getString(1)
            val pw =  result.getString(2)
            val temarray = arrayListOf(seq,id,pw)
            array.add(temarray)
        }

        result.close() // 커서를 닫아주지 않으면 다음 작업이 실행되지 않음!!!!!!

        return array
    }

    fun createaccount(database : SQLiteDatabase, name : String, column : List<String>, value : List<String>){

        var mysql = "INSERT INTO ${name} ("
        for (i in 0..column.size - 1){
            mysql += "'" + column[i] + "'"
            if (i != column.size-1){
                mysql += ", "
            }
        }
        mysql += ") VALUES ("
        for (i in 0..column.size-1){
            mysql += "'" + value[i] + "'"
            if (i != column.size-1){
                mysql += " , "
            }
        }
        mysql += ")"
        database.execSQL(mysql)
    }

    fun updateaccount(database : SQLiteDatabase, name : String, column: List<String>, value: List<String>, seq: String ){

        var mysql = "UPDATE $name SET ("
        for (i in 0..column.size-1){
            mysql += "'" + column[i] + "'" + " = " + value[i]
            if (i != column.size-1){
                mysql += ","
            }
        }
        mysql += ") WHERE seq = $seq"
        database.execSQL(mysql)
    }

    fun deleteaccount(database : SQLiteDatabase, name: String, seq : String){
        var sql = "DELETE FROM ${name} WHERE seq = ${seq}"
        database.execSQL(sql)

    }

}