package com.example.pluralofertasandroid2.fragments.home.myCupons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.OpenedCuponsAdapter
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import kotlinx.android.synthetic.main.fragment_opened_mycupons.*
import java.util.ArrayList

class OpenedMyCuponsFragment: Fragment(), RecyclerItemClickListener {
    private var openedCuponList  = ArrayList<Cupon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_opened_mycupons, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureInitialViews()
        openedCuponList.add(Cupon())
        openedCuponList.add(Cupon())
    }
    private fun configureInitialViews(){

        val openedCuponAdapter = OpenedCuponsAdapter(requireContext(),openedCuponList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        openedMyCuponsRv.layoutManager = layoutManager
        openedMyCuponsRv.adapter = openedCuponAdapter
    }

}