package com.example.pluralofertasandroid2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.cartCheckout.CartCheckoutActivity
import com.example.pluralofertasandroid2.fragments.productDetailsBottom.TabsDetailsBottomFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import kotlinx.android.synthetic.main.tool_bar.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsFragment : Fragment() {

   /* override fun onCreate(savedInstanceState: Bundle?) {
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
                *//* Log.d(TAG, "onClick: opening dialog")
                 val dialog = DialogCuponsFrag()
                 dialog.setTargetFragment(TabsDetailsBottomFragment(), 1)
                 supportFragmentManager.let { it1 -> dialog.show(it1,"DialogCuponsFrag") }*//*
            }

            startActivity(intent)
        }
    }*/
}