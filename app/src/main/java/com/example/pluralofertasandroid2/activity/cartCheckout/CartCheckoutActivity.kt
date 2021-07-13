package com.example.pluralofertasandroid2.activity.cartCheckout

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.cart.CartFragment

import com.example.pluralofertasandroid2.helper.MyUsefulKotlin


class CartCheckoutActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartcheckout)


        MyUsefulKotlin().startFragment(CartFragment(), supportFragmentManager)


    }

}