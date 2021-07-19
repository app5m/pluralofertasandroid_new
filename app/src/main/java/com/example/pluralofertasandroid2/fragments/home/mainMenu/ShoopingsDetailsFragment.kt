package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.ProductsDemandAdapter
import com.example.pluralofertasandroid2.helper.MyUseFulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.fragment_shoopingdetails.*
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

        MyUseFulKotlin().startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)


    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ProductsDemandAdapter(requireContext(),productsList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        productsShoopingDetailsRv.layoutManager = layoutManager
        productsShoopingDetailsRv.adapter = usedCuponAdapter
    }

    fun onBackPressed() {
        activity?.let {
            MyUseFulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}