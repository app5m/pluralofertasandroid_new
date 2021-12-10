package br.com.app5m.pluralofertas.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.activity_sucess.*

class SucessAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sucess)

        if(intent.extras != null) {

            val ticket = intent.extras!!.getString("ticket")

            ticket_bt.visibility = View.VISIBLE

            ticket_bt.setOnClickListener {
                Useful(this).openWebPage(ticket)
            }


        }

        ok_bt.setOnClickListener {

            startActivity(Intent(this, HomeAct::class.java))
            finishAffinity()

        }
    }
}