package com.example.pluralofertasandroid2.filter

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import kotlinx.android.synthetic.main.fragment_filtro.*
import kotlinx.android.synthetic.main.fragment_filtro.view.*


class Filtro : Fragment() {

    private lateinit var rootView: View
    private var ativouRange: Boolean = false
   // private lateinit var adapterItens: ItemPesquisaAdapter
   // private lateinit var viewModel: FiltroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view

        rootView = inflater.inflate(R.layout.fragment_filtro, container, false)

        var spin = codeSpinner
        val countryCodes = resources.getStringArray(R.array.kilometers)
        val countryCodeAdapter: ArrayAdapter<String>? = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.code_spinner_layout, R.id.textView, countryCodes
            )
        }
        spin.setAdapter(countryCodeAdapter)
        spin.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val code: String = java.lang.String.valueOf(spin.getSelectedItem())
                if (position == 0) {
                    spin.setSelection(0)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        return rootView
    } //end view



}


