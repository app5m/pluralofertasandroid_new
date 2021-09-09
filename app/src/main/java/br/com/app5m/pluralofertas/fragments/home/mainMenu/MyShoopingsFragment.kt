package br.com.app5m.pluralofertas.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ShoopingsAdapter
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Shooping
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

        MyUsefulKotlin().startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)


    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ShoopingsAdapter(requireContext(),shoopingList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        shoopingsRv.layoutManager = layoutManager
        shoopingsRv.adapter = usedCuponAdapter
    }

    fun onBackPressed() {
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}