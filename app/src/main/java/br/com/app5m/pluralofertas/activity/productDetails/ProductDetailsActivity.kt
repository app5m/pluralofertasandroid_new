package br.com.app5m.pluralofertas.activity.productDetails


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.MainActivity
import br.com.app5m.pluralofertas.fragments.dialog.CuponsDialog
import br.com.app5m.pluralofertas.fragments.tabsProductDetails.TabsDetailsBottomFragment
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.tool_bar.*


class ProductDetailsActivity : AppCompatActivity(), RecyclerItemClickListener {
    private  val TAG = "ProductDetails_Act"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)

        //toolbar
       setSupportActionBar(toolbar)
        supportActionBar?.let { MyUsefulKotlin().setActionBar(this, it,"") }
        toolbarTitle.setText("Detalhes do produto")
        toolbar.visibility = View.VISIBLE

        MyUsefulKotlin().startFragment(TabsDetailsBottomFragment(), supportFragmentManager)
        applyCupon.setOnClickListener() {

            CuponsDialog().show(supportFragmentManager, "DialogCuponsFrag")
        }
        btnComprarDetalhesProduto.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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