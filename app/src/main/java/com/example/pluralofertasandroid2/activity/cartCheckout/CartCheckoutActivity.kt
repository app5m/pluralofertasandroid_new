package com.example.pluralofertasandroid2.activity.cartCheckout

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.payment.PaymentFormFragment

import com.example.pluralofertasandroid2.helper.MyUseFulKotlin


class CartCheckoutActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartcheckout)


        MyUseFulKotlin().startFragment(PaymentFormFragment(), supportFragmentManager)


    }

}