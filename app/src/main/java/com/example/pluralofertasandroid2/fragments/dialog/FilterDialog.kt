package com.example.pluralofertasandroid2.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon
import kotlinx.android.synthetic.main.dialog_cupon.*
import kotlinx.android.synthetic.main.dialog_filter.*

class FilterDialog : DialogFragment(), RecyclerItemClickListener {
    private val TAG = "FilterDialog"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.dialog_filter, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        filterBnt.setOnClickListener {
            dialog?.dismiss();

        }
    }
}