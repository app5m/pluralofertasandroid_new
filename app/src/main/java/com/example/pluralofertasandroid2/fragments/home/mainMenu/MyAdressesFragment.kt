package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.UAddressAdapter
import com.example.pluralofertasandroid2.fragments.dialog.RegisterAdressDialog
import com.example.pluralofertasandroid2.fragments.home.HomeFragmentOffer
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.UAddress
import kotlinx.android.synthetic.main.dialog_myadresses_home.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.fragment_myadresses.*
import kotlinx.android.synthetic.main.fragment_myadresses.myAdressesRv
import kotlinx.android.synthetic.main.fragment_myadresses.saveBnt2
import kotlinx.android.synthetic.main.tool_bar.*
import java.util.*


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
        backButton.setOnClickListener {
            onbackpressed()
        }
        saveBnt2.setOnClickListener {
            onbackpressed()
        }
        addAdressMainMenuFab.setOnClickListener {
            val dialog = RegisterAdressDialog()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"DialogRegisterAdress") }
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
                MainMenuFragment(),
                it.supportFragmentManager
            )
        }

    }

}
