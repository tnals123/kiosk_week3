package com.example.kiosk_gui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class History_Fragment : Fragment(){

    var myarray = ArrayList<ArrayList<String>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //규칙임
        var myview = inflater.inflate(R.layout.historypage, container, false)
        var scrollview = myview.findViewById<ScrollView>(R.id.historyscroll)

        var myarray = (activity as Payment_Page).return_Array()
        Log.d("myarray",myarray.toString())

        var mylayout = myview.findViewById<LinearLayout>(R.id.scrolllayout)
        Log.d("zx",mylayout.toString())

        for (i in 0..myarray.size-1){

            var menusection =  LinearLayout(activity)

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

            var drinkimg = ImageView(activity)
            drinkimg.layoutParams = imageparams

            infosectionparams.setMargins(20,0,0,0)
            nameandpriceinfosection.gravity = Gravity.CENTER
            textparams2.setMargins(20,0,0,0)
            textparams.gravity = Gravity.CENTER
            textparams2.gravity = Gravity.CENTER

            menusection.layoutParams = layoutParams
            menusection.addView(drinkimg)
            menusection.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection = LinearLayout(activity)
            drinkinfosection.layoutParams = infosectionparams
            drinkinfosection.setOrientation(LinearLayout.VERTICAL)

            var drinkinfosection_nameandprice = LinearLayout(activity)
            drinkinfosection_nameandprice.layoutParams = nameandpriceinfosection
            drinkinfosection_nameandprice.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection_specific = LinearLayout(activity)
            drinkinfosection_specific.layoutParams = nameandpriceinfosection
            drinkinfosection_specific.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfo_name = TextView(activity)
            drinkinfo_name.layoutParams = textparams
            drinkinfo_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_name.setTextColor(((activity?.getApplication()!!.getResources().getColor(R.color.black))))
            drinkinfo_name.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_price = TextView(activity)
            drinkinfo_price.layoutParams = textparams2
            drinkinfo_price.setTextAppearance(R.style.boldStyle)
            drinkinfo_price.setTextColor(((activity?.getApplication()!!.getResources().getColor(R.color.black))))
            drinkinfo_price.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_specific_name = TextView(activity)
            drinkinfo_specific_name.layoutParams = textparams
            drinkinfo_specific_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_specific_name.setTextColor((activity?.getApplication()!!.getResources().getColor(R.color.blue)))
            drinkinfo_specific_name.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            drinkimg.setImageResource(myarray[i][1].toInt())
            drinkinfo_name.text = myarray[i][2]
            drinkinfo_price.text = "₩ ${myarray[i][4]}"
            drinkinfo_specific_name.text = "${myarray[i][3]} 잔"

            drinkinfosection_nameandprice.addView(drinkinfo_name)
            drinkinfosection_nameandprice.addView(drinkinfo_price)

            drinkinfosection_specific.addView(drinkinfo_specific_name)

            drinkinfosection.addView(drinkinfosection_nameandprice)
            drinkinfosection.addView(drinkinfosection_specific)

            menusection.addView(drinkinfosection)
            mylayout.addView(menusection)

        }
        return myview
    }

    fun initLayout(view : View){

        var mylayout = view.findViewById<LinearLayout>(R.id.scrolllayout)
        Log.d("zx",mylayout.toString())

        for (i in 0..myarray.size-1){

            var menusection =  LinearLayout(activity)

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

            var drinkimg = ImageView(activity)
            drinkimg.layoutParams = imageparams

            infosectionparams.setMargins(20,0,0,0)
            nameandpriceinfosection.gravity = Gravity.CENTER
            textparams2.setMargins(20,0,0,0)
            textparams.gravity = Gravity.CENTER
            textparams2.gravity = Gravity.CENTER

            menusection.layoutParams = layoutParams
            menusection.addView(drinkimg)
            menusection.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection = LinearLayout(activity)
            drinkinfosection.layoutParams = infosectionparams
            drinkinfosection.setOrientation(LinearLayout.VERTICAL)

            var drinkinfosection_nameandprice = LinearLayout(activity)
            drinkinfosection_nameandprice.layoutParams = nameandpriceinfosection
            drinkinfosection_nameandprice.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfosection_specific = LinearLayout(activity)
            drinkinfosection_specific.layoutParams = nameandpriceinfosection
            drinkinfosection_specific.setOrientation(LinearLayout.HORIZONTAL)

            var drinkinfo_name = TextView(activity)
            drinkinfo_name.layoutParams = textparams
            drinkinfo_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_name.setTextColor(((activity?.getApplication()!!.getResources().getColor(R.color.black))))
            drinkinfo_name.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_price = TextView(activity)
            drinkinfo_price.layoutParams = textparams2
            drinkinfo_price.setTextAppearance(R.style.boldStyle)
            drinkinfo_price.setTextColor(((activity?.getApplication()!!.getResources().getColor(R.color.black))))
            drinkinfo_price.setTextSize(getResources().getDimension(R.dimen.pricetextsize));

            var drinkinfo_specific_name = TextView(activity)
            drinkinfo_specific_name.layoutParams = textparams
            drinkinfo_specific_name.setTextAppearance(R.style.boldStyle)
            drinkinfo_specific_name.setTextColor((activity?.getApplication()!!.getResources().getColor(R.color.blue)))
            drinkinfo_specific_name.setTextSize(getResources().getDimension(R.dimen.smalltextsize));

            var drinkinfo_specific_price = TextView(activity)
            drinkinfo_specific_price.layoutParams = textparams2
            drinkinfo_specific_price.setTextAppearance(R.style.boldStyle)
            drinkinfo_specific_price.setTextColor((activity?.getApplication()!!.getResources().getColor(R.color.blue)))
            drinkinfo_specific_price.setTextSize(getResources().getDimension(R.dimen.smalltextsize));

            drinkimg.setImageResource(myarray[i][1].toInt())
            drinkinfo_name.text = myarray[i][2]
            drinkinfo_price.text = "₩ ${myarray[i][4]}"
            drinkinfo_specific_name.text = "${myarray[i][3]}}"

            drinkinfosection_nameandprice.addView(drinkinfo_name)
            drinkinfosection_nameandprice.addView(drinkinfo_price)

            drinkinfosection_specific.addView(drinkinfo_specific_name)
            drinkinfosection_specific.addView(drinkinfo_specific_price)

            drinkinfosection.addView(drinkinfosection_nameandprice)
            drinkinfosection.addView(drinkinfosection_specific)

            menusection.addView(drinkinfosection)
            mylayout.addView(menusection)

        }

    }
}