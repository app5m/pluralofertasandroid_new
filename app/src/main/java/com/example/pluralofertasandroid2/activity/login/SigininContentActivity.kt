package com.example.pluralofertasandroid2.activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.login.SigninContentFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin

class SigininContentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_content)

        MyUsefulKotlin().startFragment(SigninContentFragment(), supportFragmentManager)



    }





}