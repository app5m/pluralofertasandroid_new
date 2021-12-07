package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ShoopingsAdapter
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import br.com.app5m.pluralofertas.model.Shopping
import kotlinx.android.synthetic.main.fragment_myshoopings.*
import java.util.ArrayList

class MyShoopingsFragment: Fragment(), RecyclerItemClickListener {

    private var shoopingList  = ArrayList<Shopping>()

    private lateinit var useful: Useful

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_myshoopings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())

        btnVoltarMeuPerfilTv.setOnClickListener{
            requireActivity().onBackPressed()
        }

        configureInitialViews()
        shoopingList.add(Shopping())
        shoopingList.add(Shopping())
        shoopingList.add(Shopping())
        shoopingList.add(Shopping())
        shoopingList.add(Shopping())
        shoopingList.add(Shopping())

    }
    override fun onClickListenerShoopings(shopping: Shopping) {

        useful.startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)


    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ShoopingsAdapter(requireContext(),shoopingList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        shoopingsRv.layoutManager = layoutManager
        shoopingsRv.adapter = usedCuponAdapter
    }

}