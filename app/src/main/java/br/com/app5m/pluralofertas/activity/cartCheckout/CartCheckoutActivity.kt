package br.com.app5m.pluralofertas.activity.cartCheckout

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.fragments.payment.PaymentFormFragment
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin


class CartCheckoutActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartcheckout)


        MyUsefulKotlin().startFragment(PaymentFormFragment(), supportFragmentManager)


    }

}