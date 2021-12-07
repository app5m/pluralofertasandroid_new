package br.com.app5m.pluralofertas.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.fragment.payment.PaymentFormFragment
import br.com.app5m.pluralofertas.util.Useful


class CartCheckoutActivity : AppCompatActivity(){

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartcheckout)

        useful = Useful(this)

        useful.startFragment(PaymentFormFragment(), supportFragmentManager)


    }

}