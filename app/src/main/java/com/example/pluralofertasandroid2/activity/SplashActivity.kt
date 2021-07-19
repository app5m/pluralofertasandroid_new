package com.example.pluralofertasandroid2.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        start()

    }
    private fun start() {
        Handler().postDelayed({

                startActivity(Intent(this@SplashActivity, IntroAct::class.java))
                finish()
        }, 4000)
    }

}