package com.example.pluralofertasandroid2.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.UAddressAdapter
import com.example.pluralofertasandroid2.fragments.home.mainMenu.MainMenuFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.UAddress
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.fragment_myadresses.*
import kotlinx.android.synthetic.main.fragment_myadresses.myAdressesRv
import kotlinx.android.synthetic.main.fragment_myadresses_home.*
import kotlinx.android.synthetic.main.tool_bar.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [MyAdressesHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyAdressesHomeFragment : Fragment(), RecyclerItemClickListener {
    private lateinit var viewFragment: View
    private var uaddressList  = ArrayList<UAddress>()
    var me: Fragment = this
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view



        val viewFragment = inflater.inflate(R.layout.fragment_myadresses_home, container, false)


        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButtonHome.setOnClickListener {
            onbackpressed()
        }
        saveBnt.setOnClickListener {
            onbackpressed()
        }


        configureInitialViews()
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())


    }
    private fun configureInitialViews(){

        val productsAdapter = UAddressAdapter(requireContext(),uaddressList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        myAdressesRv.layoutManager = layoutManager
        myAdressesRv.adapter = productsAdapter
    }
    fun onbackpressed(){
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(
                HomeFragmentOffer(),
                it.supportFragmentManager
            )
        }

    }

}
