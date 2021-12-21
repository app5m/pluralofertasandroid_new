package br.com.app5m.pluralofertas.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.app5m.pluralofertas.R
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        if (intent.extras != null) {
            val voucher = intent.extras!!.getString("voucherCode")

            voucherCode_tv.text = voucher


        }

        ok_bt.setOnClickListener {
            startActivity(Intent(this, HomeAct::class.java))
            finishAffinity()

        }

    }
}