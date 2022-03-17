package com.example.kiosk_gui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class Payment_Step1_Fragment : Fragment() {

    var whatselected_step1: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //규칙임
        var myview = inflater.inflate(R.layout.payment_step1_fragment, container, false)
        var requestvalue = arguments?.getString("price")

        var fragment = Payment_Fragment()
        var mybundle = Bundle()
        var nextbutton = activity?.findViewById<Button>(R.id.nextbutton)

        nextbutton?.isClickable = false
        nextbutton?.setBackgroundResource(R.drawable.disalbed_button)
        mybundle.putString("price", requestvalue.toString())
        fragment.arguments = mybundle

        step1_Selected(
            myview.findViewById(R.id.phone),
            myview.findViewById(R.id.barcode),
            myview.findViewById(R.id.no),
            myview.findViewById(R.id.number_choice),
            myview.findViewById(R.id.barcode_choice),
            myview.findViewById(R.id.no_choice)
        )
        return myview
    }

    fun step1_Selected(
        imageview1: ImageView, imageview2: ImageView, imageview3: ImageView,
        textview1: TextView, textview2: TextView, textview3: TextView) {
        var nextbutton = activity?.findViewById<Button>(R.id.nextbutton)
        imageview1.setOnClickListener() {

            textview1.setTextColor(getResources().getColor(R.color.red))
            textview2.setTextColor(getResources().getColor(R.color.blue))
            textview3.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step1 = textview1.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }
        imageview2.setOnClickListener() {

            textview2.setTextColor(getResources().getColor(R.color.red))
            textview1.setTextColor(getResources().getColor(R.color.blue))
            textview3.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step1 = textview2.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }
        imageview3.setOnClickListener() {

            textview3.setTextColor(getResources().getColor(R.color.red))
            textview1.setTextColor(getResources().getColor(R.color.blue))
            textview2.setTextColor(getResources().getColor(R.color.blue))

            whatselected_step1 = textview3.text.toString()
            nextbutton?.isClickable = true
            nextbutton?.setBackgroundResource(R.drawable.blue_button)
        }

    }
}
