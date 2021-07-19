package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.ShoopingsAdapter
import com.example.pluralofertasandroid2.helper.MyUseFulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Shooping
import kotlinx.android.synthetic.main.fragment_myshoopings.*
import java.util.ArrayList

class MyShoopingsFragment: Fragment(), RecyclerItemClickListener {
    private var shoopingList  = ArrayList<Shooping>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_myshoopings, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVoltarMeuPerfilTv.setOnClickListener{
            onBackPressed()
        }

        configureInitialViews()
        shoopingList.add(Shooping())
        shoopingList.add(Shooping())
        shoopingList.add(Shooping())
        shoopingList.add(Shooping())
        shoopingList.add(Shooping())
        shoopingList.add(Shooping())

    }
    override fun onClickListenerShoopings(shooping: Shooping) {

        MyUseFulKotlin().startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)


    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ShoopingsAdapter(requireContext(),shoopingList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        shoopingsRv.layoutManager = layoutManager
        shoopingsRv.adapter = usedCuponAdapter
    }

    fun onBackPressed() {
        activity?.let {
            MyUseFulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}