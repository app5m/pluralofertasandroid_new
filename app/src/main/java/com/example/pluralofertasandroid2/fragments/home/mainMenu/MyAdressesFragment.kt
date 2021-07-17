package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.UAddressAdapter
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.UAddress
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.fragment_myadresses.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [MyAdressesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyAdressesFragment : Fragment(), RecyclerItemClickListener {
    private lateinit var viewFragment: View
    private var uaddressList  = ArrayList<UAddress>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_myadresses, container, false)


        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVoltarLoginButton.setOnClickListener {
            onBackPressed()
        }
        configureInitialViews()
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())

    }
    private fun configureInitialViews(){

        val productsAdapter = UAddressAdapter(requireContext(),uaddressList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        myAdressesRv.layoutManager = layoutManager
        myAdressesRv.adapter = productsAdapter
    }
    fun onBackPressed() {
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}