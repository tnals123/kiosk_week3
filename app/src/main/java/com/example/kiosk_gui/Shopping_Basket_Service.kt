package com.example.kiosk_gui
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit

class Shopping_Basket_Service : Service() {

    var shopping_basket = mutableListOf<List<String>>()
    var deleted_drink_price : Int = 0
    lateinit var channel_id : String
    lateinit var channel_name : String
    lateinit var descriptionText : String
    lateinit var channel : NotificationChannel
    var finalprice : Int = 0

    inner class MyBinder: Binder() {
        fun getService(): Shopping_Basket_Service {
            Log.d("test",shopping_basket.toString())
            return this@Shopping_Basket_Service
        }
    }

    override fun onCreate() {
        Log.d("test","생성되었습니다")
        super.onCreate()
    }

    val binder = MyBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("test1",shopping_basket.toString())

        channel_id = "mynotice"
        channel_name = "mynotice_name"
        descriptionText = ""
        size_Control()
        for (i in 0..shopping_basket.size-1){
            var asdf = shopping_basket[i][1]
            descriptionText = descriptionText + asdf + ", "
            finalprice+=shopping_basket[i][7].toInt()
        }

        var importance = NotificationManager.IMPORTANCE_DEFAULT
        channel = NotificationChannel(channel_id,channel_name,importance).apply { description = descriptionText }

        var notification = NotificationCompat.Builder(this,  "mynotice").
        setSmallIcon(R.drawable.americano).setContentTitle("현재 장바구니입니다").setContentText(descriptionText + finalprice.toString() + "원").setOngoing(true)

        var notificationmanger : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationmanger.createNotificationChannel(channel)
        notificationmanger.notify(1,notification.build())

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {

        Log.d("test5",shopping_basket.toString())
        return binder   // 클라이언트에 바인더 전달(Connection 콜백에서 수신 처리)
    }

    fun put_In_Shopping_Basket(intent: Intent){

        val data1 = intent!!.getBundleExtra("drinkinfomation")
        var drinkinfo_drinkimage = data1!!.getString("drinkimage")!!
        var drinkinfo_drinkname = data1!!.getString("drinkname")!!
        var drinkinfo_drinknumber = data1!!.getString("drinknumber")!!
        var drinkinfo_drinksize = data1!!.getString("drinksize")!!
        var drinkinfo_drinktemporature = data1!!.getString("drinktemporature")!!
        var drinkinfo_drinkperl = data1!!.getString("drinkextraperl")!!
        var drinkinfo_drinkice = data1!!.getString("drinkextraice")!!
        var drinkinfo_drinkprice = data1!!.getString("drinkprice")!!
        var drinkinfo_drinkupdated = data1!!.getString("isdrinkupdated")!!

        var tempor_list= listOf<String>(drinkinfo_drinkimage,drinkinfo_drinkname,drinkinfo_drinknumber,drinkinfo_drinksize,
            drinkinfo_drinktemporature,drinkinfo_drinkperl,drinkinfo_drinkice,drinkinfo_drinkprice,drinkinfo_drinkupdated)

        shopping_basket.add(tempor_list)

    }

    fun delete_Shopping_Basket(intent: Intent) : MutableList<List<String>> {
        val data1 = intent!!.getIntExtra("deletenum", 0)
        var tempor = mutableListOf<String>()
        shopping_basket.removeAt(data1)
        shopping_basket.add(data1,tempor)
        return shopping_basket
    }

    fun delete_Menu_Price(intent: Intent) : Int {
        val data1 = intent!!.getIntExtra("deletenum", 0)
        deleted_drink_price = shopping_basket[data1][7].toInt()
        return deleted_drink_price
    }

    fun change_Shopping_Basket_Menu(intent: Intent) : MutableList<List<String>>{
        val data1 = intent!!.getBundleExtra("drinkinfomation")
        var drinkinfo_drinkimage = data1!!.getString("drinkimage")!!
        var drinkinfo_drinkname = data1!!.getString("drinkname")!!
        var drinkinfo_drinknumber = data1!!.getString("drinknumber")!!
        var drinkinfo_drinksize = data1!!.getString("drinksize")!!
        var drinkinfo_drinktemporature = data1!!.getString("drinktemporature")!!
        var drinkinfo_drinkperl = data1!!.getString("drinkextraperl")!!
        var drinkinfo_drinkice = data1!!.getString("drinkextraice")!!
        var drinkinfo_drinkprice = data1!!.getString("drinkprice")!!
        var drinkinfo_drinkupdated = data1!!.getString("isdrinkupdated")!!
        var change_num = data1!!.getString("changenum")!!

        var tempor_list= listOf<String>(drinkinfo_drinkimage,drinkinfo_drinkname,drinkinfo_drinknumber,drinkinfo_drinksize,
            drinkinfo_drinktemporature,drinkinfo_drinkperl,drinkinfo_drinkice,drinkinfo_drinkprice,drinkinfo_drinkupdated)

        shopping_basket.set(change_num.toInt(),tempor_list)

        return shopping_basket
    }

    fun size_Control(){

        if (shopping_basket.size >=1) {

            var drinklist_array_tempor = mutableListOf<List<String>>()

            for (i in 0..shopping_basket.size - 1) {

                if (shopping_basket[i] != listOf<String>()) {
                    drinklist_array_tempor.add(shopping_basket[i])
                }
            }
            shopping_basket = drinklist_array_tempor
        }
    }

    fun return_Shopping_Basket() : MutableList<List<String>> {
        return shopping_basket
    }

    override fun onDestroy() {
        Log.d("Service", "서비스가 종료되었습니다.")
        super.onDestroy()
    }
}
