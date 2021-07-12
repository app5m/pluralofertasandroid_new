package com.example.pluralofertasandroid2.fragments.productDetailsBottom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import kotlinx.android.synthetic.main.fragment_options_tab.*


class TabOptionsFrag: Fragment() {
    private  val TAG = "TabOptionsFrag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_tab, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        this.miguito.setOnClickListener() {
            Log.d(TAG, "onClick: opening dialog")
            val dialog = DialogCuponsFrag()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"MyCustomDialog") }
        }
    }
}