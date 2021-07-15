package com.example.pluralofertasandroid2.activity.offerDetails


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.cartCheckout.CartCheckoutActivity
import com.example.pluralofertasandroid2.fragments.dialog.DialogCuponsFrag
import com.example.pluralofertasandroid2.fragments.productDetailsBottom.TabsDetailsBottomFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.google.android.material.internal.ContextUtils.getActivity
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

        btnComprarDetalhesProduto.setOnClickListener {
            val intent = Intent(this, CartCheckoutActivity::class.java)

            this.miguito.setOnClickListener() {
               /* Log.d(TAG, "onClick: opening dialog")
                val dialog = DialogCuponsFrag()
                dialog.setTargetFragment(TabsDetailsBottomFragment(), 1)
                supportFragmentManager.let { it1 -> dialog.show(it1,"DialogCuponsFrag") }*/
            }

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