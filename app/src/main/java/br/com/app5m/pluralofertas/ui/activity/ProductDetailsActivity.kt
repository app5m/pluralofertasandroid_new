package br.com.app5m.pluralofertas.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.dialog.CuponsDialog
import br.com.app5m.pluralofertas.ui.fragment.tabsProductDetails.TabsDetailsBottomFragment
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.toolbar.*


class ProductDetailsActivity : AppCompatActivity(), RecyclerItemClickListener {

    private  val TAG = "ProductDetails_Act"

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)
        setSupportActionBar(toolbar)

        useful = Useful(this)

        supportActionBar?.let { useful.setActionBar(this, it,"", 0) }
        toolbarTitle.setText("Detalhes do produto")
        toolbar.visibility = View.VISIBLE

        useful.startFragment(TabsDetailsBottomFragment(), supportFragmentManager)

        applyCupon.setOnClickListener {

            CuponsDialog().show(supportFragmentManager, "DialogCuponsFrag")
        }

        btnComprarDetalhesProduto.setOnClickListener {
            val intent = Intent(this, HomeAct::class.java)
            intent.putExtra("CHANGE_NAV_CART", "true")
            startActivity(intent)
            finishAffinity()
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