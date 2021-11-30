package br.com.app5m.pluralofertas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.activity.HomeAct
import br.com.app5m.pluralofertas.activity.IntroAct
import br.com.app5m.pluralofertas.helper.Preferences

class MainAct : AppCompatActivity() {

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

                startActivity(Intent(this, HomeAct::class.java))
            } else {

                if (preferences.getInt(
                        preferences.ENTERING_FIRST_TIME, 1) == 1) {

                    //Salva informação de que o usuário já entrou no app a primeira vez
                    preferences.storeInt(preferences.ENTERING_FIRST_TIME, 0)
                    //Exibe saudação
                    startActivity(Intent(this, IntroAct::class.java))


                } else {


                    startActivity(Intent(this, HomeAct::class.java))

                }
            }

            finishAffinity()
        }, 3500)
    }

}