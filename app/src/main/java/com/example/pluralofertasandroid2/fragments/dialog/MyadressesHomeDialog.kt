package com.example.pluralofertasandroid2.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.CuponsAdapter
import com.example.pluralofertasandroid2.adapter.UAddressAdapter
import com.example.pluralofertasandroid2.fragments.home.mainMenu.MainMenuFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import com.example.pluralofertasandroid2.model.UAddress
import kotlinx.android.synthetic.main.dialog_cupon.*
import kotlinx.android.synthetic.main.dialog_myadresses_home.*
import kotlinx.android.synthetic.main.fragment_myadresses.*
import kotlinx.android.synthetic.main.fragment_myadresses.addAdressMainMenuFab
import kotlinx.android.synthetic.main.fragment_myadresses.myAdressesRv
import java.util.ArrayList

class MyadressesHomeDialog: DialogFragment(), RecyclerItemClickListener {
    private val TAG = "MyadressesHomeDialog"
    private lateinit var viewFragment: View
    private var uaddressList  = ArrayList<UAddress>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.AppTheme)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_myadresses_home, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButtonHome.setOnClickListener {

            onbackpressed()
        }
        saveBnt.setOnClickListener {
            onbackpressed()
        }
        addAdressHomeFab.setOnClickListener {
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

        dialog?.dismiss()

    }


}