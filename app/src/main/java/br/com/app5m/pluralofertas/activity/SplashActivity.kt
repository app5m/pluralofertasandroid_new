package br.com.app5m.pluralofertas.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.Preferences

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = Preferences(this)

        start()

    }


    private fun start() {
        Handler().postDelayed({

            if (preferences.getLogin()) {

                startActivity(Intent(this, MainActivity::class.java))
            } else {

                if (preferences.getInt(
                        preferences.ENTERING_FIRST_TIME, 1) == 1) {

                    //Salva informação de que o usuário já entrou no app a primeira vez
                    preferences.storeInt(preferences.ENTERING_FIRST_TIME, 0)
                    //Exibe saudação
                    startActivity(Intent(this, IntroAct::class.java))


                } else {


                    startActivity(Intent(this, MainActivity::class.java))

                }
            }

            finishAffinity()
        }, 3500)
    }

}