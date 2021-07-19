package com.example.pluralofertasandroid2.fragments.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.home.mainMenu.MainMenuFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_form_of_payment_creditcard.*


/**
 * A simple [Fragment] subclass.
 * Use the [form_of_payment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFormCreditCardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_of_payment_creditcard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backBnt2.setOnClickListener {
            onbackpressed()
        }
    }
    fun onbackpressed(){
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(
                PaymentFormFragment(),
                it.supportFragmentManager
            )
        }
    }
}
