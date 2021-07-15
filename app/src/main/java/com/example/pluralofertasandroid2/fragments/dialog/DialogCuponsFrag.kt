package com.example.pluralofertasandroid2.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.model.Cupon
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.dialog_cupon.*
import kotlinx.android.synthetic.main.fragment_options_tab.*
import java.util.ArrayList

class DialogCuponsFrag: DialogFragment() {
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
        ApllyBnt.setOnClickListener {
            dialog?.dismiss();

        }


    }
}