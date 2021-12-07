package br.com.app5m.pluralofertas.ui.fragment.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.app5m.pluralofertas.R
import kotlinx.android.synthetic.main.fragment_form_of_payment_creditcard.*


/**
 * A simple [Fragment] subclass.
 */
class PaymentFormCreditCardFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var spinner: Spinner? = null
    var list_of_items = arrayOf("1x", "2x", "3x","4x", "5x" , "6x","7x", "8x" , "9x", "10x","11x", "12x" )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_of_payment_creditcard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = this.mySpinnerPeymentCredit
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, list_of_items) }
        // Set layout to use when the list of choices appear

        if (aa != null) {
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        backBnt2.setOnClickListener {
            requireActivity().onBackPressed()
        }

        adressCreditCardtEt.setOnClickListener {
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
