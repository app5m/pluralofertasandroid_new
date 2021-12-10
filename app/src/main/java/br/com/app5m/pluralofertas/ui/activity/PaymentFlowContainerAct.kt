package br.com.app5m.pluralofertas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.ui.fragment.payment_flow.ChooseMethodPaymentFrag
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.toolbar.*

class PaymentFlowContainerAct : AppCompatActivity() {

    private lateinit var useful: Useful
    var fullDataPurchase = Request()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_flow_container)
        setSupportActionBar(toolbar)

        useful = Useful(this)

        useful.setActionBar(this, supportActionBar!!, "Pagamento", 0)

        useful.startFragment(ChooseMethodPaymentFrag(), supportFragmentManager)

        if (intent.extras != null) {

            fullDataPurchase.idCart = intent.extras!!.getString("idCart")
            fullDataPurchase.idAddress = intent.extras!!.getString("idAddress")

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}