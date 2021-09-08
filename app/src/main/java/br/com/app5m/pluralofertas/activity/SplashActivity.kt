package br.com.app5m.pluralofertas.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R

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