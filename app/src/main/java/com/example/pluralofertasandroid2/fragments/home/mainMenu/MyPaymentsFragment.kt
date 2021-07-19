package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.PaymentsAdapter

import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Payment
import com.example.pluralofertasandroid2.model.Shooping

import kotlinx.android.synthetic.main.fragment_mypayments.*
import java.util.ArrayList

class MyPaymentsFragment: Fragment(), RecyclerItemClickListener {
    private var paymentsList  = ArrayList<Payment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_mypayments, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backPaymentBnt.setOnClickListener{
            onBackPressed()
        }
        configureInitialViews()
        paymentsList.add(Payment())
        paymentsList.add(Payment())
        paymentsList.add(Payment())
        paymentsList.add(Payment())
        paymentsList.add(Payment())
        paymentsList.add(Payment())

    }
    private fun configureInitialViews(){

        val usedCuponAdapter = PaymentsAdapter(requireContext(),paymentsList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        ceditCardPaymentsRv.layoutManager = layoutManager
        ceditCardPaymentsRv.adapter = usedCuponAdapter
    }
    fun onBackPressed() {
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }


}