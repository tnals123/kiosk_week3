package com.example.kiosk_gui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class Payment_Fragment : Fragment() {
    override fun onCreateView(inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?):View{

        var view =  inflater.inflate(R.layout.payment_fragment,container,false)
        var pricetext = view.findViewById<TextView>(R.id.price)

        pricetext.text = "감사합니다 또오세용~"

        return view
    }

}