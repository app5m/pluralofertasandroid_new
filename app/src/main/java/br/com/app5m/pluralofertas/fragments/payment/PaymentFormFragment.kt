package br.com.app5m.pluralofertas.fragments.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_form_of_payment.*


/**
 * A simple [Fragment] subclass.
 * Use the [form_of_payment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFormFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_of_payment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credCardChoiceBnt.setOnClickListener {
            MyUsefulKotlin().startFragment(PaymentFormCreditCardFragment(), requireActivity().supportFragmentManager)

        }
        ticketChoiceBnt.setOnClickListener {
            MyUsefulKotlin().startFragment(PaymentFormTicketFragment(), requireActivity().supportFragmentManager)

        }
        backBnt4.setOnClickListener {
            activity?.finish()
        }
    }

}