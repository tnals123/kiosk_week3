///*
//package com.example.kiosk_gui
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.Service
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.os.Build
//import android.os.Bundle
//import android.os.IBinder
//import android.util.Log
//import androidx.core.app.NotificationCompat
//import androidx.localbroadcastmanager.content.LocalBroadcastManager
//import java.lang.NullPointerException
//
//class Notification_Service : Service(){
//
//    lateinit var channel : NotificationChannel
//    lateinit var drinkinfo_drinkname : String
//    lateinit var drinkinfo_drinkprice : String
//    lateinit var channel_id : String
//    lateinit var channel_name :String
//    lateinit var descriptionText :String
//    var clicked : Int = 0
//
//    var shopping_basket = mutableListOf<List<String>>()
//    var finalprice : Int = 0
//
//    override fun onBind(p0: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
//            IntentFilter("shopping_basket")
//        )
//    }
//    fun foo(){
//    }
//
//    val mBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent?) {
//
//            val mybundle = intent!!.getBundleExtra("shopping_basket")
//            if (mybundle != null) {
//
//                var drinkinfo_drinkimage = mybundle!!.getString("drinkimage")!!
//                var drinkinfo_drinkname = mybundle!!.getString("drinkname")!!
//                var drinkinfo_drinknumber = mybundle!!.getString("drinknumber")!!
//                var drinkinfo_drinksize = mybundle!!.getString("drinksize")!!
//                var drinkinfo_drinktemporature = mybundle!!.getString("drinktemporature")!!
//                var drinkinfo_drinkperl = mybundle!!.getString("drinkextraperl")!!
//                var drinkinfo_drinkice = mybundle!!.getString("drinkextraice")!!
//                var drinkinfo_drinkprice = mybundle!!.getString("drinkprice")!!
//                var drinkinfo_drinkupdated = mybundle!!.getString("isdrinkupdated")!!
//
//                var tempor_basket = mutableListOf<String>()
//
//                tempor_basket.add(drinkinfo_drinkimage)
//                tempor_basket.add(drinkinfo_drinkname)
//                tempor_basket.add(drinkinfo_drinknumber)
//                tempor_basket.add(drinkinfo_drinksize)
//                tempor_basket.add(drinkinfo_drinktemporature)
//                tempor_basket.add(drinkinfo_drinkperl)
//                tempor_basket.add(drinkinfo_drinkice)
//                tempor_basket.add(drinkinfo_drinkprice)
//                tempor_basket.add(drinkinfo_drinkupdated)
//
//                shopping_basket.add(tempor_basket)
//                finalprice += drinkinfo_drinkprice.toInt()
//                clicked += 1
//
//                if (shopping_basket.size >= 1) {
//
//                    var drinklist_array_tempor = mutableListOf<List<String>>()
//
//                    for (i in 0..shopping_basket.size - 1) {
//
//                        if (shopping_basket[i] != listOf<String>()) {
//                            drinklist_array_tempor.add(shopping_basket[i])
//                        }
//                    }
//                    shopping_basket = drinklist_array_tempor
//                }
//                Log.d("이거 제발 되라 제발 되라 ", shopping_basket.toString())
//            }
//        }
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//        var name = intent!!.getStringExtra("delete")
//        var price = intent!!.getStringExtra("deleteprice")
//        var number = intent!!.getStringExtra("deletenum")
//
//        if (name != null){
//            var shopping_basktet_deleted = listOf<String>()
//            shopping_basket.removeAt(number!!.toInt())
//            shopping_basket.add(number!!.toInt(),shopping_basktet_deleted)
//            finalprice -= price!!.toInt()
//            clicked -= 1
//
//        }
//
//        channel_id = "mynotice"
//        channel_name = "mynotice_name"
//        descriptionText = ""
//        for (i in 0..shopping_basket.size-1){
//            try {
//                var drinkname = shopping_basket[i][1]
//                descriptionText = descriptionText + drinkname + " "
//            }
//            catch (e: IndexOutOfBoundsException){
//                foo()
//            }
//        }*/
///*//*
//
//
//        var importance = NotificationManager.IMPORTANCE_DEFAULT
//        channel = NotificationChannel(channel_id,channel_name,importance).apply { description = descriptionText }
//
//        var notification = NotificationCompat.Builder(this,  "mynotice").
//        setSmallIcon(R.drawable.americano).setContentTitle("현재 장바구니입니다").
//        setContentText("{${descriptionText}} \n 총 금액 : ${finalprice} 음료 개수 : ${clicked } 개 ")
//
//        var notificationmanger : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationmanger.createNotificationChannel(channel)
//        notificationmanger.notify(1,notification.build())
//
//        return super.onStartCommand(intent, flags, startId)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver)
//    }
//}*/