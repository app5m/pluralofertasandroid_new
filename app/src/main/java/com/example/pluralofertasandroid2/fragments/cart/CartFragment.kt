package com.example.pluralofertasandroid2.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.payment.PaymentFormFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_cart, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPagarCarrinhoFinalizarCompraButton2.setOnClickListener {
            MyUsefulKotlin().startFragment(PaymentFormFragment(), requireActivity().supportFragmentManager)
        }
    }
}