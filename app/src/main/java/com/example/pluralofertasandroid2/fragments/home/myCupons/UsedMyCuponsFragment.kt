package com.example.pluralofertasandroid2.fragments.home.myCupons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.UsedCuponsAdapter
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import kotlinx.android.synthetic.main.fragment_used_mycupons.*
import java.util.ArrayList

class UsedMyCuponsFragment : Fragment(), RecyclerItemClickListener {
    private var usedCuponList  = ArrayList<Cupon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_used_mycupons, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureInitialViews()
        usedCuponList.add(Cupon())
        usedCuponList.add(Cupon())
        usedCuponList.add(Cupon())
        usedCuponList.add(Cupon())
        usedCuponList.add(Cupon())
        usedCuponList.add(Cupon())


    }
    private fun configureInitialViews(){

        val usedCuponAdapter = UsedCuponsAdapter(requireContext(),usedCuponList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        usedMyCuponsRv.layoutManager = layoutManager
        usedMyCuponsRv.adapter = usedCuponAdapter
    }
}
