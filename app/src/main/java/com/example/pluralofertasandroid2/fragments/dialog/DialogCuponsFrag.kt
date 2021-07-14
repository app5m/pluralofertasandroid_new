package com.example.pluralofertasandroid2.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pluralofertasandroid2.R
import kotlinx.android.synthetic.main.dialog_cupon.*
import kotlinx.android.synthetic.main.fragment_options_tab.*

class DialogCuponsFrag: DialogFragment() {
    private val TAG = "DialogCuponsFrag"
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
           /* val fragment:TabOptionsFrag = TabOptionsFrag().getFragmentManager()?.findFragmentByTag("TabOptionsFrag") as TabOptionsFrag
            val valueShare:String*/
            if(radioButton2.isChecked) {
              /*  Toast.makeText(context, textView3.text.toString(), Toast.LENGTH_SHORT).show()
                valueShare = radioButton2.text.toString()
                fragment.miguito.setText(valueShare)*/

            }
            if(radioButton3.isChecked) {
/*
                Toast.makeText(context, textView4.text.toString(), Toast.LENGTH_SHORT).show()
*/
            }

        }
    }
}