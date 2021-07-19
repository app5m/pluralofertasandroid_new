package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.productDetails.ProductDetailsActivity
import com.example.pluralofertasandroid2.adapter.ProductsAdapter
import com.example.pluralofertasandroid2.adapter.ProductsDemandAdapter
import com.example.pluralofertasandroid2.adapter.ShoopingsAdapter
import com.example.pluralofertasandroid2.adapter.UsedCuponsAdapter
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import com.example.pluralofertasandroid2.model.Product
import com.example.pluralofertasandroid2.model.Shooping
import kotlinx.android.synthetic.main.fragment_mypayments.*
import kotlinx.android.synthetic.main.fragment_mypayments.btnVoltarMeuPerfilTv
import kotlinx.android.synthetic.main.fragment_myshoopings.*
import kotlinx.android.synthetic.main.fragment_myshoopings.shoopingsRv
import kotlinx.android.synthetic.main.fragment_shoopingdetails.*
import kotlinx.android.synthetic.main.fragment_used_mycupons.*
import kotlinx.android.synthetic.main.fragment_used_mycupons.usedMyCuponsRv
import java.util.ArrayList

class ShoopingsDetailsFragment: Fragment(), RecyclerItemClickListener {
    private var productsList  = ArrayList<Product>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_shoopingdetails, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVoltarMeuPerfilTv.setOnClickListener{
            onBackPressed()
        }

        configureInitialViews()
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())

    }
    override fun onClickListenerProducts(product: Product) {

        MyUsefulKotlin().startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)


    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ProductsDemandAdapter(requireContext(),productsList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        productsShoopingDetailsRv.layoutManager = layoutManager
        productsShoopingDetailsRv.adapter = usedCuponAdapter
    }

    fun onBackPressed() {
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}