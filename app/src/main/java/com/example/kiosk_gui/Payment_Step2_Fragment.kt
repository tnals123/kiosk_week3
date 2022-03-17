package com.example.kiosk_gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Payment_Step2_Fragment : Fragment() {

    var whatselected_step2: String = ""

    override fun onCreateView(inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?):View {
        //규칙임
        var myview = inflater.inflate(R.layout.payment_step2_fragment, container, false)
        var requestvalue  = arguments?.getString("price")

        var fragment = Payment_Fragment()
        var mybundle = Bundle()
        mybundle.putString("price", requestvalue.toString())
        fragment.arguments = mybundle
        var nextbutton = activity?.findViewById<Button>(R.id.nextbutton)

        nextbutton?.isClickable = false
        nextbutton?.setBackgroundResource(R.drawable.disalbed_button)

        step2_Selected(
            myview.findViewById(R.id.card),
            myview.findViewById(R.id.ediyacard),
            myview.findViewById(R.id.ediyacoupon),
            myview.findViewById(R.id.ediyagiftcard),
            myview.findViewById(R.id.card_choice),
            myview.findViewById(R.id.ediyacard_choice),
            myview.findViewById(R.id.ediyacoupon_choice),
            myview.findViewById(R.id.ediyagifcard_choice)

        )

        return myview
    }

    fun step2_Selected(
        imageview1: ImageView, imageview2: ImageView, imageview3: ImageView,imageview4: ImageView,
        textview1: TextView, textview2: TextView, textview3: TextView , textview4: TextView) {

        var nextbutton = activity?.findViewById<Button>(R.id.nextbutton)
        imageview1.setOnClickListener() {

            textview1.setTextColor(getResources().getColor(R.color.red))
            textview2.setTextColor(getResources().getColor(R.color.blue))
            textview3.setTextColor(getResources().getColor(R.color.blue))
            textview4.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step2 = textview1.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }

        imageview2.setOnClickListener() {

            textview2.setTextColor(getResources().getColor(R.color.red))
            textview1.setTextColor(getResources().getColor(R.color.blue))
            textview3.setTextColor(getResources().getColor(R.color.blue))
            textview4.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step2 = textview2.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }

        imageview3.setOnClickListener() {

            textview3.setTextColor(getResources().getColor(R.color.red))
            textview1.setTextColor(getResources().getColor(R.color.blue))
            textview2.setTextColor(getResources().getColor(R.color.blue))
            textview4.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step2 = textview3.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }

        imageview4.setOnClickListener() {

            textview4.setTextColor(getResources().getColor(R.color.red))
            textview1.setTextColor(getResources().getColor(R.color.blue))
            textview2.setTextColor(getResources().getColor(R.color.blue))
            textview3.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step2 = textview4.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }

    }


}