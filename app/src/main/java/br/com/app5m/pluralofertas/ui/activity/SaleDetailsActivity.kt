package br.com.app5m.pluralofertas.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.toolbar.*


class SaleDetailsActivity : AppCompatActivity(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var saleControl: SaleControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        saleControl = SaleControl(this, this, useful)

        supportActionBar?.let { useful.setActionBar(this, it,"", 0) }

        if (intent.extras != null) {
            intent.getStringExtra("idSale")?.let { saleControl.listIdSales(it) }
        }

//        applyCupon.setOnClickListener {
//
//            CuponsDialog().show(supportFragmentManager, "DialogCuponsFrag")
//        }
//
//        btnComprarDetalhesProduto.setOnClickListener {
//            val intent = Intent(this, HomeAct::class.java)
//            intent.putExtra("CHANGE_NAV_CART", "true")
//            startActivity(intent)
//            finishAffinity()
//        }

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