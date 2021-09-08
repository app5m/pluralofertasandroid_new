package br.com.app5m.pluralofertas.fragments.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.fragments.dialog.MyadressesDialog
import br.com.app5m.pluralofertas.helper.MyUseFulKotlin
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_form_of_payment_creditcard.*
import kotlinx.android.synthetic.main.fragment_form_of_payment_ticket.*
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [form_of_payment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFormTicketFragment : Fragment(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_of_payment_ticket, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adressTicketEt.setOnClickListener {

            val dialog = MyadressesDialog()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"MyadressesDialog") }
        }

        backTicketBnt.setOnClickListener {
            onbackpressed()
        }
    }
    fun onbackpressed(){
        activity?.let {
            MyUseFulKotlin().startFragmentOnBack(
                PaymentFormFragment(),
                it.supportFragmentManager
            )
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
