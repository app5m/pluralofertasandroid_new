package com.example.pluralofertasandroid2.activity.offerDetails


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.cartCheckout.CartCheckoutActivity
import com.example.pluralofertasandroid2.fragments.dialog.DialogCuponsFrag
import com.example.pluralofertasandroid2.fragments.productDetailsBottom.TabsDetailsBottomFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.menu_scrolling.*
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
        miguito.setOnClickListener() {

            DialogCuponsFrag().show(supportFragmentManager, "DialogCuponsFrag")
        }
        btnComprarDetalhesProduto.setOnClickListener {
            val intent = Intent(this, CartCheckoutActivity::class.java)
            startActivity(intent)

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