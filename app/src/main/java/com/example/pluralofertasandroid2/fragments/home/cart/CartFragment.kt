package com.example.pluralofertasandroid2.fragments.home.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.cartCheckout.CartCheckoutActivity
import com.example.pluralofertasandroid2.adapter.CartAdapter
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cart
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.ArrayList

class CartFragment : Fragment(), RecyclerItemClickListener {
    private var cartList  = ArrayList<Cart>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_cart, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPagarCarrinhoFinalizarCompraButton2.setOnClickListener {
/*
            MyUsefulKotlin().startFragment(PaymentFormFragment(), requireActivity().supportFragmentManager)*/

           val intent = Intent(activity, CartCheckoutActivity::class.java)
            startActivity(intent)

        }

        configureInitialViews()
        cartList.add(Cart())
        cartList.add(Cart())
        cartList.add(Cart())
        cartList.add(Cart())

        /*cartAdapterRv*/

    }

    private fun configureInitialViews(){

        val productsAdapter = CartAdapter(requireContext(),cartList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        cartAdapterRv.layoutManager = layoutManager
        cartAdapterRv.adapter = productsAdapter
    }
}