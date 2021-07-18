package com.example.pluralofertasandroid2.activity.productDetails


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.MainActivity
import com.example.pluralofertasandroid2.activity.cartCheckout.CartCheckoutActivity
import com.example.pluralofertasandroid2.fragments.dialog.CuponsFragDialog
import com.example.pluralofertasandroid2.fragments.tabsProductDetails.TabsDetailsBottomFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.tool_bar.*


class ProductDetailsActivity : AppCompatActivity(), RecyclerItemClickListener{
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

            CuponsFragDialog().show(supportFragmentManager, "DialogCuponsFrag")
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