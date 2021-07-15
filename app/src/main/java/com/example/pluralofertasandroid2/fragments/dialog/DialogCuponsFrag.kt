package com.example.pluralofertasandroid2.fragments.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.offerDetails.ProductDetailsActivity
import com.example.pluralofertasandroid2.adapter.CuponsAdapter
import com.example.pluralofertasandroid2.adapter.ProductsAdapter
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.dialog_cupon.*
import kotlinx.android.synthetic.main.fragment_options_tab.*
import kotlinx.android.synthetic.main.home_body.*
import java.util.ArrayList

class DialogCuponsFrag: DialogFragment(), RecyclerItemClickListener {
    private val TAG = "DialogCuponsFrag"

    var recyclerProduct: RecyclerView? = null
    private var cupons  = ArrayList<Cupon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_cupon, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureInitialViews()
        cupons.add(Cupon())

        ApllyBnt.setOnClickListener {
            dialog?.dismiss();
            Toast.makeText(context, "Cupom aplicado", Toast.LENGTH_SHORT).show()

        }
    }
    fun configureInitialViews(){
        val cuponssAdapter = CuponsAdapter(requireContext(),cupons,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)

        cuponsRv.layoutManager = layoutManager

        cuponsRv.adapter = cuponssAdapter


    }

    override fun onClickListenerCupons(cupon: Cupon) {
    }

}