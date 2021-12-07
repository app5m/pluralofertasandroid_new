package br.com.app5m.pluralofertas.fragment.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ProductsDemandAdapter
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.helper.Useful
import br.com.app5m.pluralofertas.model.Product
import kotlinx.android.synthetic.main.fragment_shoopingdetails.*
import java.util.ArrayList

class ShoopingsDetailsFragment: Fragment(), RecyclerItemClickListener {

    private var productsList  = ArrayList<Product>()

    private lateinit var useful: Useful

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_shoopingdetails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())

        btnVoltarMeuPerfilTv.setOnClickListener{
            requireActivity().onBackPressed()
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

        useful.startFragment(ShoopingsDetailsFragment(), requireActivity().supportFragmentManager)

    }

    private fun configureInitialViews(){

        val usedCuponAdapter = ProductsDemandAdapter(requireContext(),productsList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        productsShoopingDetailsRv.layoutManager = layoutManager
        productsShoopingDetailsRv.adapter = usedCuponAdapter
    }

}