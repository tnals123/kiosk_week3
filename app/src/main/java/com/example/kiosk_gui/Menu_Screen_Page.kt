package com.example.kiosk_gui

import android.app.Service
import android.content.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.widget.*
import androidx.appcompat.app.AlertDialog
import android.view.WindowManager
import androidx.core.content.ContextCompat
import java.nio.BufferUnderflowException
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.NullPointerException
import java.util.*


class Menu_Screen_Page : AppCompatActivity() {

    lateinit var myService: Shopping_Basket_Service

    var drinklist_array = mutableListOf<List<String>>()
    var drinklist_array2 = mutableListOf<List<String>>()
    var final_price_payment: Int = 0
    var arraysize: Int = drinklist_array.size
    var isService = false


    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menuscreen)
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

        var intent = Intent(this, Shopping_Basket_Service::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        update_Menu_Screen("menu_coffee_jpg", "menu_coffee_name", "menu_coffee_price")
        menu_Select_Button()
    }

    override fun onStop() {
        val intent = Intent(this, Shopping_Basket_Service::class.java)
        startService(intent)
        super.onStop()
    }

    override fun onDestroy() {
        val intent = Intent(this, Shopping_Basket_Service::class.java)
        super.onDestroy()
    }

    fun find_Final_Price(){
        final_price_payment = 0
        for (i in 0..drinklist_array2.size-1){
            final_price_payment += drinklist_array2[i][7].toInt()
        }
    }

//    fun menu_Update() {
//
//        Log.d("test4", drinklist_array2.size.toString())
//
//        val menu_name_text_params = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT)
//
//        var shopping_basktet = findViewById<LinearLayout>(R.id.shopping_basktet_menu_list)
//
//        shopping_basktet.removeAllViews()
//
//    for (i in 0..drinklist_array2.size-1) {
//
//        final_price_payment += drinklist_array2[i][7].toInt()
//
//        if (drinklist_array2[i] != listOf<String>()) {
//            val menu_name_text: TextView = TextView(this)
//
//            menu_name_text.setTextAppearance(R.style.boldStyle)
//            menu_name_text.setTextColor(
//                (getApplication().getResources().getColor(R.color.black))
//            )
//
//            menu_name_text.layoutParams = menu_name_text_params
//            menu_name_text.text =
//                "${drinklist_array2[i][1]} ${drinklist_array2[i][2]}잔 , ${drinklist_array2[i][3]} , ${drinklist_array2[i][4]} ," +
//                        " ${drinklist_array2[i][5]} , ${drinklist_array2[i][6]} , ${drinklist_array2[i][7]} 원"
//
//            shopping_basktet.addView(menu_name_text)
//        }
//    }
//}

    fun put_Drink_In_Shopping_Basktet(drink_jpg : String ,drink_name : String , drink_number : String, drink_size : String,
                                      drink_temperature : String,
                                      extraperl :String, extraice :String, final_price : String, origin_price : String){


        final_price_payment += final_price.toInt()

        var clickbutton = findViewById<Button>(R.id.paymentbutton)
        var shopping_basktet = findViewById<LinearLayout>(R.id.shopping_basktet_menu_list)
        var menu_name_text = TextView(this)

        val menu_name_text_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        menu_name_text!!.setTextAppearance(R.style.boldStyle)
        menu_name_text!!.setTextColor((getApplication().getResources().getColor(R.color.black)))

        menu_name_text.layoutParams = menu_name_text_params
        menu_name_text.text = "${drink_name} ${drink_number}잔 , ${drink_size} , ${drink_temperature} ," +
                " ${extraperl} , ${extraice} , ${final_price} 원"

        shopping_basktet.addView(menu_name_text)

        clickbutton.setOnClickListener(){
            popup_Window_Shopping_Basktet(drink_jpg  ,drink_name  , drink_number , drink_size ,
                drink_temperature ,
                extraperl, extraice, final_price )
        }

    }

    fun popup_Window_Shopping_Basktet(drink_jpg : String ,drink_name : String , drink_number : String, drink_size : String,
                                      drink_temperature : String,
                                      extraperl :String, extraice :String, final_price : String){


        var popupView = getLayoutInflater().inflate(R.layout.popupwindow_shopping_basket, null);
        var alertdialog = AlertDialog.Builder(this).create()

        var shoppingbasketscreen = popupView.findViewById<LinearLayout>(R.id.shopping_basktetscreen)
        var cancelbutton = popupView.findViewById<Button>(R.id.paycancelbutton)
        var paybutton = popupView.findViewById<Button>(R.id.paybutton)

        myService!!.size_Control()
        drinklist_array2 = myService!!.return_Shopping_Basket()

        for (i in 0..drinklist_array2.size-1){

            var menusection =  LinearLayout(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val imageparams = LinearLayout.LayoutParams(
                300,300
            )
            val infosectionparams = LinearLayout.LayoutParams(
                700,300
            )
            val nameandpriceinfosection = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1.0f
            )
            val textparams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            val textparams2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            val buttonsectionparams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                300
            )

            val buttonparams = LinearLayout.LayoutParams(
                50, 50,
            )

            var buttonsection = LinearLayout(this)
            buttonsection.layoutParams = buttonsectionparams
            buttonsection.setOrientation(LinearLayout.VERTICAL)

            var delete_button = ImageView(this)
            delete_button.layoutParams = buttonparams
            delete_button.setBackgroundColor(Color.TRANSPARENT)
            delete_button.setBackgroundResource(R.drawable.deletebutton)

            var correction_button = ImageView(this)
            correction_button.layoutParams = buttonparams
            correction_button.setBackgroundColor(Color.TRANSPARENT)
            correction_button.setBackgroundResource(R.drawable.edit_button)

            var drinkimg = ImageView(this)
            drinkimg.layoutParams = imageparams

            infosectionparams.setMargins(20,0,0,0)
            nameandpriceinfosection.gravity = Gravity.CENTER
            textparams2.setMargins(20,0,0,0)
            buttonparams.setMargins(0,0,0,20)
            textparams.gravity = Gravity.CENTER
            textparams2.gravity = Gravity.CENTER

            buttonsection.addView(delete_button)
            buttonsection.addView(correction_button)

            menusection.layoutParams = layoutParams
            menusection.addView(drinkimg)
            menusection.addView(buttonsection)
            menusection.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection = LinearLayout(this)
            drinkinfosection.layoutParams = infosectionparams
            drinkinfosection.setOrientation(LinearLayout.VERTICAL)

            var drinkinfosection_nameandprice = LinearLayout(this)
            drinkinfosection_nameandprice.layoutParams = nameandpriceinfosection
            drinkinfosection_nameandprice.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection_specific = LinearLayout(this)
            drinkinfosection_specific.layoutParams = nameandpriceinfosection
            drinkinfosection_specific.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfo_name = TextView(this)
            drinkinfo_name.layoutParams = textparams
            drinkinfo_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_name.setTextColor(((getApplication().getResources().getColor(R.color.black))))
            drinkinfo_name.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_price = TextView(this)
            drinkinfo_price.layoutParams = textparams2
            drinkinfo_price.setTextAppearance(R.style.boldStyle)
            drinkinfo_price.setTextColor(((getApplication().getResources().getColor(R.color.black))))
            drinkinfo_price.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_specific_name = TextView(this)
            drinkinfo_specific_name.layoutParams = textparams
            drinkinfo_specific_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_specific_name.setTextColor(((getApplication().getResources().getColor(R.color.blue))))
            drinkinfo_specific_name.setTextSize(getResources().getDimension(R.dimen.smalltextsize));

            var drinkinfo_specific_price = TextView(this)
            drinkinfo_specific_price.layoutParams = textparams2
            drinkinfo_specific_price.setTextAppearance(R.style.boldStyle)
            drinkinfo_specific_price.setTextColor(((getApplication().getResources().getColor(R.color.blue))))
            drinkinfo_specific_price.setTextSize(getResources().getDimension(R.dimen.smalltextsize));

            drinkimg.setImageResource(drinklist_array2[i][0].toInt())
            drinkinfo_name.text = drinklist_array2[i][1]
            drinkinfo_price.text = "₩ ${drinklist_array2[i][7]}"
            drinkinfo_specific_name.text = "${drinklist_array2[i][3]},${drinklist_array2[i][4]},${drinklist_array2[i][5]},${drinklist_array2[i][6]}"
            drinkinfo_specific_price.text = "${drinklist_array2[i][7].toInt()/drinklist_array2[i][2].toInt()}X${drinklist_array2[i][2]}"

            drinkinfosection_nameandprice.addView(drinkinfo_name)
            drinkinfosection_nameandprice.addView(drinkinfo_price)

            drinkinfosection_specific.addView(drinkinfo_specific_name)
            drinkinfosection_specific.addView(drinkinfo_specific_price)

            drinkinfosection.addView(drinkinfosection_nameandprice)
            drinkinfosection.addView(drinkinfosection_specific)

            menusection.addView(drinkinfosection)

            correction_button.setOnClickListener(){
                alertdialog.hide()
                popup_Window_Menu_Info(drinklist_array2[i][0].toInt(),drinklist_array2[i][1],
                                        drinklist_array2[i][7],drinklist_array2[i][2],
                                        drinklist_array2[i][3],drinklist_array2[i][5],drinklist_array2[i][4],
                                        drinklist_array2[i][6],drinklist_array2[i][8],i)
            }

            delete_button.setOnClickListener(){

                var shoppingbasketservice = Intent(this,Shopping_Basket_Service::class.java)
                shoppingbasketservice.putExtra("deletenum",i)

                var shopping_basktet = findViewById<LinearLayout>(R.id.shopping_basktet_menu_list)
                Log.d("asdfasdf",i.toString())
                var price = myService!!.delete_Menu_Price(shoppingbasketservice)
                drinklist_array2 = myService!!.delete_Shopping_Basket(shoppingbasketservice)

                final_price_payment -= price

                Log.d("삭제 후 장바구니",drinklist_array2.toString())

                shoppingbasketscreen.removeView(menusection)

                shopping_basktet.removeAllViews()
                arraysize = drinklist_array2.size

                val menu_name_text_params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                for (i in 0..drinklist_array2.size-1) {

                    if (drinklist_array2[i] != listOf<String>()) {
                        val menu_name_text: TextView = TextView(this)

                        menu_name_text.setTextAppearance(R.style.boldStyle)
                        menu_name_text.setTextColor(
                            (getApplication().getResources().getColor(R.color.black))
                        )

                        menu_name_text.layoutParams = menu_name_text_params
                        menu_name_text.text =
                            "${drinklist_array2[i][1]} ${drinklist_array2[i][2]}잔 , ${drinklist_array2[i][3]} , ${drinklist_array2[i][4]} ," +
                                    " ${drinklist_array2[i][5]} , ${drinklist_array2[i][6]} , ${drinklist_array2[i][7]} 원"

                        shopping_basktet.addView(menu_name_text)
                    }
                }
            }

            shoppingbasketscreen.addView(menusection)
        }

        cancelbutton.setOnClickListener(){
            alertdialog.hide()
        }

        paybutton.setOnClickListener(){
            find_Final_Price()
            println(final_price_payment)
            alertdialog.hide()
            var nextpage = Intent(this,Payment_Page::class.java)
            nextpage.putExtra("data", final_price_payment.toString())
            startActivity(nextpage)
        }

        alertdialog.setView(popupView)
        alertdialog.show()
        alertdialog.window!!.setLayout(1093,1760)

    }

    fun popup_Window_Menu_Info( what_I_touched_jpg : Int, what_I_touched_name : String, what_I_touched_price : String,
                                WHEN_MENU_UPDATE_menu_number : String,
                                WHEN_MENU_UPDATE_menu_size : String, WHEN_MENU_UPDATE_menu_perl : String,
                                WHEN_MENU_UPDATE_menu_temporature : String, WHEN_MENU_UPDATE_menu_ice : String,
                                WHEN_MENU_UPDATE_menu_origin_price : String, WHEN_MENU_UPDATE_what_to_change : Int){
        //팝업창 기능

        drinklist_array2 = myService!!.return_Shopping_Basket()

        var popupView = getLayoutInflater().inflate(R.layout.popupwindow, null);
        var alertdialog = AlertDialog.Builder(this).create()

        var drinkjpg = popupView.findViewById<ImageView>(R.id.drinkjpg)
        drinkjpg!!.setBackgroundResource(what_I_touched_jpg)

        var drinkname = popupView.findViewById<TextView>(R.id.drinkname)
        drinkname.text = what_I_touched_name

        var drinkprice = popupView.findViewById<TextView>(R.id.price_text)
        drinkprice!!.setText(what_I_touched_price)

        var origin_drink_price = drinkprice.text
        var origin_drink_string = origin_drink_price.toString()
        var origin_drink_int = origin_drink_string.toInt()
        var drink_final_price : Int = 0
        var extraperl_price : Int = 0
        var extrasize_price : Int = 0
        var WHEN_MENU_UPDATE_drink_extrasize : Int = 0
        var WHEN_MENU_UPDATE_drink_extraperl : Int = 0
        var WHEN_MENU_UPDATE_what_to_change_num : Int = WHEN_MENU_UPDATE_what_to_change

        var number = popupView.findViewById<TextView>(R.id.number_text)
        var minus_button = popupView.findViewById<Button>(R.id.minus_button)
        var plus_button = popupView.findViewById<Button>(R.id.plus_button)
        var hot_button = popupView.findViewById<Button>(R.id.hot_button)
        var ice_button = popupView.findViewById<Button>(R.id.ice_button)
        var regular_button = popupView.findViewById<Button>(R.id.regular_button)
        var extra_button = popupView.findViewById<Button>(R.id.extra_button)
        var extraperlbutton = popupView.findViewById<Button>(R.id.extraperl)
        var no_extraperlbutton = popupView.findViewById<Button>(R.id.no_extraperl)
        var extraicebutton = popupView.findViewById<Button>(R.id.extraice)
        var no_extraicebutton = popupView.findViewById<Button>(R.id.no_extraice )
        var cancel_button = popupView.findViewById<Button>(R.id.extra_order_cancel_button)
        var submit_button = popupView.findViewById<Button>(R.id.extra_order_submit_button)
        var issizeupbuttonclicked = false
        var isperlupbuttonclicked = false

        var drink_number = number.text
        var drink_number_string = drink_number.toString()
        var drink_number_int = drink_number_string.toInt()

        if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED"){
            number.text = WHEN_MENU_UPDATE_menu_number
            drinkprice.text = what_I_touched_price

            if (WHEN_MENU_UPDATE_menu_temporature == "HOT"){
                ice_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
                hot_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                ice_button.setBackgroundResource(R.drawable.blue_line)
                hot_button.setBackgroundResource(R.drawable.red_button)
            }
            else{
                hot_button.setTextColor((getApplication().getResources().getColor(R.color.red)))
                ice_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                hot_button.setBackgroundResource(R.drawable.red_line)
                ice_button.setBackgroundResource(R.drawable.blue_button)
            }

            if (WHEN_MENU_UPDATE_menu_size == "Regular"){
                issizeupbuttonclicked = false
                regular_button.setBackgroundResource(R.drawable.blue_button)
                extra_button.setBackgroundResource(R.drawable.white_button)
                regular_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                extra_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }
            else{
                issizeupbuttonclicked = true
                WHEN_MENU_UPDATE_drink_extrasize = 500
                extra_button.setBackgroundResource(R.drawable.blue_button)
                regular_button.setBackgroundResource(R.drawable.white_button)
                extra_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                regular_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }

            if (WHEN_MENU_UPDATE_menu_perl == "펄 추가 X"){
                isperlupbuttonclicked = false
                no_extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                extraperlbutton.setBackgroundResource(R.drawable.white_button)
                no_extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }
            else{
                isperlupbuttonclicked = true
                WHEN_MENU_UPDATE_drink_extraperl = 500
                extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                no_extraperlbutton.setBackgroundResource(R.drawable.white_button)
                extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                no_extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }

            if (WHEN_MENU_UPDATE_menu_ice == "얼음 추가 X"){
                no_extraicebutton.setBackgroundResource(R.drawable.blue_button)
                extraicebutton.setBackgroundResource(R.drawable.white_button)
                no_extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }
            else{
                extraicebutton.setBackgroundResource(R.drawable.blue_button)
                no_extraicebutton.setBackgroundResource(R.drawable.white_button)
                extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                no_extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            }
        }

        minus_button.setOnClickListener(){

            drink_number = number.text
            drink_number_string = drink_number.toString()
            drink_number_int = drink_number_string.toInt()

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED") {

                if (drink_number_int > 1) {
                    drink_number_int -= 1
                    number.text = drink_number_int.toString()

                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }
            }

            else {

                if (drink_number_int > 1) {
                    drink_number_int = drink_number_int - 1
                    number.text = drink_number_int.toString()
                    drink_final_price = origin_drink_int + extraperl_price + extrasize_price
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }
            }
        }

        plus_button.setOnClickListener(){

            drink_number = number.text
            drink_number_string = drink_number.toString()
            drink_number_int = drink_number_string.toInt()

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED") {

                if (drink_number_int < 10) {
                    drink_number_int += 1
                    number.text = drink_number_int.toString()

                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()
                }
            }

            else {

                if (drink_number_int < 10) {
                    drink_number_int = drink_number_int + 1
                    number.text = drink_number_int.toString()
                    drink_final_price = origin_drink_int + extraperl_price + extrasize_price
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }
            }
        }

        hot_button.setOnClickListener(){

            ice_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
            hot_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
            ice_button.setBackgroundResource(R.drawable.blue_line)
            hot_button.setBackgroundResource(R.drawable.red_button)
        }

        ice_button.setOnClickListener(){

            hot_button.setTextColor((getApplication().getResources().getColor(R.color.red)))
            ice_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
            hot_button.setBackgroundResource(R.drawable.red_line)
            ice_button.setBackgroundResource(R.drawable.blue_button)
        }

        regular_button.setOnClickListener(){

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED"){

                if (issizeupbuttonclicked == true) {
                    regular_button.setBackgroundResource(R.drawable.blue_button)
                    extra_button.setBackgroundResource(R.drawable.white_button)
                    regular_button.setTextColor(
                        (getApplication().getResources().getColor(R.color.white))
                    )
                    extra_button.setTextColor(
                        (getApplication().getResources().getColor(R.color.blue))
                    )
                    issizeupbuttonclicked = false
                    //가격 내리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    WHEN_MENU_UPDATE_drink_extrasize -= 500
                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()
                }

            }

            else {
                if (issizeupbuttonclicked == true) {
                    regular_button.setBackgroundResource(R.drawable.blue_button)
                    extra_button.setBackgroundResource(R.drawable.white_button)
                    regular_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                    extra_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
                    issizeupbuttonclicked = false
                    //가격 내리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    extrasize_price -= 500
                    drink_final_price =
                        (origin_drink_int + extrasize_price + extraperl_price) * drink_number_int
                    drinkprice.text = drink_final_price.toString()

                }
            }
        }

        extra_button.setOnClickListener(){
            //가격 추가 때문에 계속 클릭 못하게 함

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED"){

                if (issizeupbuttonclicked == false) {
                    extra_button.setBackgroundResource(R.drawable.blue_button)
                    regular_button.setBackgroundResource(R.drawable.white_button)
                    extra_button.setTextColor((getApplication().getResources().getColor(R.color.white)))
                    regular_button.setTextColor((getApplication().getResources().getColor(R.color.blue)))
                    issizeupbuttonclicked = true
                    //가격 내리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    WHEN_MENU_UPDATE_drink_extrasize += 500
                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }

            }

            else {
                if (issizeupbuttonclicked == false) {
                    extra_button.setBackgroundResource(R.drawable.blue_button)
                    regular_button.setBackgroundResource(R.drawable.white_button)
                    extra_button.setTextColor(
                        (getApplication().getResources().getColor(R.color.white))
                    )
                    regular_button.setTextColor(
                        (getApplication().getResources().getColor(R.color.blue))
                    )
                    issizeupbuttonclicked = true
                    //가격 올리기

                    extrasize_price += 500
                    drink_final_price =
                        (origin_drink_int + extrasize_price + extraperl_price) * drink_number_int
                    drinkprice.text = drink_final_price.toString()
                }
            }
        }

        extraperlbutton.setOnClickListener(){

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED"){

                if (isperlupbuttonclicked == false){

                    extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                    no_extraperlbutton.setBackgroundResource(R.drawable.white_button)
                    extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                    no_extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
                    isperlupbuttonclicked = true
                    //가격 올리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    WHEN_MENU_UPDATE_drink_extraperl += 500
                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }

            }

            else {

                if (isperlupbuttonclicked == false) {

                    extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                    no_extraperlbutton.setBackgroundResource(R.drawable.white_button)
                    extraperlbutton.setTextColor(
                        (getApplication().getResources().getColor(R.color.white))
                    )
                    no_extraperlbutton.setTextColor(
                        (getApplication().getResources().getColor(R.color.blue))
                    )
                    isperlupbuttonclicked = true
                    //가격 올리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    extraperl_price += 500
                    drink_final_price =
                        (origin_drink_int + extraperl_price + extrasize_price) * drink_number_int
                    drinkprice.text = drink_final_price.toString()

                }
            }
        }

        no_extraperlbutton.setOnClickListener(){

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED"){

                if (isperlupbuttonclicked == true){

                    no_extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                    extraperlbutton.setBackgroundResource(R.drawable.white_button)
                    no_extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
                    extraperlbutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
                    isperlupbuttonclicked = false
                    //가격 내리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    WHEN_MENU_UPDATE_drink_extraperl -= 500
                    drink_final_price = WHEN_MENU_UPDATE_menu_origin_price.toInt() + WHEN_MENU_UPDATE_drink_extraperl + WHEN_MENU_UPDATE_drink_extrasize
                    drinkprice.text = (drink_final_price * drink_number_int).toString()

                }
            }

            else {

                if (isperlupbuttonclicked == true) {

                    no_extraperlbutton.setBackgroundResource(R.drawable.blue_button)
                    extraperlbutton.setBackgroundResource(R.drawable.white_button)
                    no_extraperlbutton.setTextColor(
                        (getApplication().getResources().getColor(R.color.white))
                    )
                    extraperlbutton.setTextColor(
                        (getApplication().getResources().getColor(R.color.blue))
                    )
                    isperlupbuttonclicked = false
                    //가격 내리기

                    drink_number_string = number.text.toString()
                    drink_number_int = drink_number_string.toInt()

                    extraperl_price -= 500
                    drink_final_price =
                        (origin_drink_int + extraperl_price + extrasize_price) * drink_number_int
                    drinkprice.text = drink_final_price.toString()
                }
            }
        }

        //색 변화 코드

        extraicebutton.setOnClickListener(){

            extraicebutton.setBackgroundResource(R.drawable.blue_button)
            no_extraicebutton.setBackgroundResource(R.drawable.white_button)
            extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
            no_extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
        }

        no_extraicebutton.setOnClickListener(){

            no_extraicebutton.setBackgroundResource(R.drawable.blue_button)
            extraicebutton.setBackgroundResource(R.drawable.white_button)
            no_extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.white)))
            extraicebutton.setTextColor((getApplication().getResources().getColor(R.color.blue)))
        }

        cancel_button.setOnClickListener(){
            alertdialog.hide()
        }

        submit_button.setOnClickListener(){

            drinklist_array2 = myService!!.return_Shopping_Basket()

            alertdialog.hide()

            var temperature: String = ""
            var mysize: String = ""
            var extraorder: String = ""
            var extraorder_ice: String = ""

            if (hot_button.currentTextColor.toString() == "-1") {
                temperature = "HOT"
            } else {
                temperature = "ICE"
            }

            if (regular_button.currentTextColor.toString() == "-1") {
                mysize = "Regular"
            } else {
                mysize = "Extra"
            }

            if (extraperlbutton.currentTextColor.toString() == "-1") {
                extraorder = "펄 추가"
            } else {
                extraorder = "펄 추가 X"
            }

            if (extraicebutton.currentTextColor.toString() == "-1") {
                extraorder_ice = "얼음 추가"
            } else {
                extraorder_ice = "얼음 추가 X"
            }

            if (WHEN_MENU_UPDATE_menu_number != "NOT REQUIRED") {

                var shopping_basktet = findViewById<LinearLayout>(R.id.shopping_basktet_menu_list)

                var shoppingbasket_service = Intent(this,Shopping_Basket_Service::class.java)
                var shoppingbasket_bundle = Bundle()

                shoppingbasket_bundle.putString("drinkimage", what_I_touched_jpg.toString())
                shoppingbasket_bundle.putString("drinkname", what_I_touched_name)
                shoppingbasket_bundle.putString("drinknumber", number.text.toString())
                shoppingbasket_bundle.putString("drinksize", mysize)
                shoppingbasket_bundle.putString("drinktemporature", temperature)
                shoppingbasket_bundle.putString("drinkextraperl", extraorder)
                shoppingbasket_bundle.putString("drinkextraice", extraorder_ice)
                shoppingbasket_bundle.putString("drinkprice", drinkprice.text.toString())
                shoppingbasket_bundle.putString("isdrinkupdated", WHEN_MENU_UPDATE_menu_origin_price)
                shoppingbasket_bundle.putString("changenum",WHEN_MENU_UPDATE_what_to_change_num.toString())

                shoppingbasket_service.putExtra("drinkinfomation", shoppingbasket_bundle)

                drinklist_array2 = myService!!.change_Shopping_Basket_Menu(shoppingbasket_service)

                val menu_name_text_params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                shopping_basktet.removeAllViews()
                Log.d("오류나기 전", drinklist_array2.toString())
                myService!!.size_Control()
                drinklist_array2 = myService!!.return_Shopping_Basket()
                for (i in 0..drinklist_array2.size-1) {
                    Log.d("수정", drinklist_array2.toString())
                    final_price_payment += drinklist_array2[i][7].toInt()

                    if (drinklist_array2[i] != listOf<String>()) {
                        val menu_name_text: TextView = TextView(this)

                        menu_name_text.setTextAppearance(R.style.boldStyle)
                        menu_name_text.setTextColor(
                            (getApplication().getResources().getColor(R.color.black))
                        )

                        menu_name_text.layoutParams = menu_name_text_params
                        menu_name_text.text =
                            "${drinklist_array2[i][1]} ${drinklist_array2[i][2]}잔 , ${drinklist_array2[i][3]} , ${drinklist_array2[i][4]} ," +
                                    " ${drinklist_array2[i][5]} , ${drinklist_array2[i][6]} , ${drinklist_array2[i][7]} 원"

                        shopping_basktet.addView(menu_name_text)
                    }
                }
            }

            else {


                var shoppingbasket_service = Intent(this,Shopping_Basket_Service::class.java)
                var shoppingbasket_bundle = Bundle()

                shoppingbasket_bundle.putString("drinkimage", what_I_touched_jpg.toString())
                shoppingbasket_bundle.putString("drinkname", what_I_touched_name)
                shoppingbasket_bundle.putString("drinknumber", number.text.toString())
                shoppingbasket_bundle.putString("drinksize", mysize)
                shoppingbasket_bundle.putString("drinktemporature", temperature)
                shoppingbasket_bundle.putString("drinkextraperl", extraorder)
                shoppingbasket_bundle.putString("drinkextraice", extraorder_ice)
                shoppingbasket_bundle.putString("drinkprice", drinkprice.text.toString())
                shoppingbasket_bundle.putString("isdrinkupdated", WHEN_MENU_UPDATE_menu_origin_price)

                shoppingbasket_service.putExtra("drinkinfomation", shoppingbasket_bundle)
                myService!!.put_In_Shopping_Basket(shoppingbasket_service)
                drinklist_array2 = myService!!.return_Shopping_Basket()

                put_Drink_In_Shopping_Basktet(
                    what_I_touched_jpg.toString(),
                    what_I_touched_name,
                    number.text.toString(),
                    mysize,
                    temperature,
                    extraorder,
                    extraorder_ice,
                    drinkprice.text.toString(),
                    WHEN_MENU_UPDATE_menu_origin_price

                )

            }
        }

        alertdialog.setView(popupView)
        alertdialog.show()
        alertdialog.window!!.setLayout(1093,1760)
    }

    fun menu_Select_Button(){
        //화면 동적 업데이트 사용 함수
        var menusection = findViewById<LinearLayout>(R.id.menusection)
        var coffee_button = findViewById<Button>(R.id.coffeebutton)
        var aid_button = findViewById<Button>(R.id.aidbutton)
        var tea_button = findViewById<Button>(R.id.teabutton)
        var flat_button = findViewById<Button>(R.id.flatbutton)

        coffee_button!!.setOnClickListener(){
            menusection.removeAllViews()
            update_Menu_Screen("menu_coffee_jpg","menu_coffee_name","menu_coffee_price")
            button_Clicked_Color()
            coffee_button.setBackgroundResource(R.drawable.menuscreen_button_clicked)
            coffee_button.setTextColor(getApplication().getResources().getColor(R.color.white))
        }

        aid_button!!.setOnClickListener(){
            menusection.removeAllViews()
            update_Menu_Screen("asdf1","asdf2","asdf3")
            button_Clicked_Color()
            aid_button.setBackgroundResource(R.drawable.menuscreen_button_clicked)
            aid_button.setTextColor(getApplication().getResources().getColor(R.color.white))
        }

        tea_button!!.setOnClickListener(){
            menusection.removeAllViews()
            update_Menu_Screen("menu_coffee_jpg","menu_coffee_name","menu_coffee_price")
            button_Clicked_Color()
            tea_button.setBackgroundResource(R.drawable.menuscreen_button_clicked)
            tea_button.setTextColor(getApplication().getResources().getColor(R.color.white))
        }

        flat_button!!.setOnClickListener(){
            menusection.removeAllViews()
            update_Menu_Screen("asdf1","asdf2","asdf3")
            button_Clicked_Color()
            flat_button.setBackgroundResource(R.drawable.menuscreen_button_clicked)
            flat_button.setTextColor(getApplication().getResources().getColor(R.color.white))
        }
    }

    fun button_Clicked_Color(){

        var coffee_button = findViewById<Button>(R.id.coffeebutton)
        var aid_button = findViewById<Button>(R.id.aidbutton)
        var tea_button = findViewById<Button>(R.id.teabutton)
        var flat_button = findViewById<Button>(R.id.flatbutton)

        coffee_button.setBackgroundResource(R.drawable.menuscreen_button)
        aid_button.setBackgroundResource(R.drawable.menuscreen_button)
        tea_button.setBackgroundResource(R.drawable.menuscreen_button)
        flat_button.setBackgroundResource(R.drawable.menuscreen_button)

        coffee_button.setTextColor(getApplication().getResources().getColor(R.color.black))
        aid_button.setTextColor(getApplication().getResources().getColor(R.color.black))
        tea_button.setTextColor(getApplication().getResources().getColor(R.color.black))
        flat_button.setTextColor(getApplication().getResources().getColor(R.color.black))
    }

    fun update_Menu_Screen(drink_array_jpg: String, drink_name: String, drink_price: String){
        //메뉴 동적 업데이트 함수
        var menusection = findViewById<LinearLayout>(R.id.menusection)

        var drink_array_id = resources.getIdentifier(drink_array_jpg,"array",packageName)
        var drink_array = resources.obtainTypedArray(drink_array_id)

        var drink_name_id = resources.getIdentifier(drink_name,"array",packageName)
        var drink_name_array = resources.getStringArray(drink_name_id)

        var drink_price_id = resources.getIdentifier(drink_price,"array",packageName)
        var drink_price_array = resources.getIntArray(drink_price_id)

        for(i in 1.. Math.ceil((drink_array.length().toDouble()/4.0)).toInt()){
            var menusection_width =  LinearLayout(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            menusection_width.setOrientation(LinearLayout.HORIZONTAL)
            menusection_width.layoutParams = layoutParams
            menusection.addView(menusection_width)


            for(first_line in (i-1)*4..(i-1)*4+3){

                var menu_box = LinearLayout(this)

                val menubox_layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,1.0f
                )

                val menuimage_layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams(280,280)
                )

                val menuname_layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams(280,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                )

                menu_box!!.layoutParams = menubox_layoutParams
                menu_box!!.setOrientation(LinearLayout.VERTICAL)

                var menu_image = ImageView(this)
                menu_image!!.layoutParams = menuimage_layoutParams

                var menu_name = TextView(this)
                menu_name!!.layoutParams = menuname_layoutParams
                menu_name!!.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                menu_name!!.setTextAppearance(R.style.boldStyle)

                var menu_price = TextView(this)
                menu_price!!.layoutParams = menuname_layoutParams
                menu_price!!.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                menu_price!!.setTextAppearance(R.style.boldStyle)

                menu_box!!.addView(menu_image)
                menu_box!!.addView(menu_name)
                menu_box!!.addView((menu_price))
                menusection_width.addView(menu_box)

                try {
                    menu_image!!.setImageResource(drink_array.getResourceId(first_line, 0))
                    menu_name!!.setText(drink_name_array[first_line])
                    menu_price!!.setText("${drink_price_array[first_line]}원")

                    menu_image!!.setOnClickListener() {
                        popup_Window_Menu_Info(
                            drink_array.getResourceId(first_line, 0),
                            drink_name_array[first_line],
                            drink_price_array[first_line].toString(),
                            "NOT REQUIRED", "NOT REQUIRED",
                            "NOT REQUIRED", "NOT REQUIRED", "NOT REQUIRED",
                            drink_price_array[first_line].toString(), 0
                        )

                    }
                }

                catch (e: ArrayIndexOutOfBoundsException){
                    foo()
                }
            }
        }
    }

    fun foo(){

    }

}
