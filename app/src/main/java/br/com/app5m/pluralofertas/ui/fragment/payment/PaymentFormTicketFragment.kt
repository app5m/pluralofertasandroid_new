package br.com.app5m.pluralofertas.ui.fragment.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_form_of_payment_ticket.*


/**
 * A simple [Fragment] subclass.
 */
class PaymentFormTicketFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var useful: Useful

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_of_payment_ticket, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())

        adressTicketEt.setOnClickListener {

        }

        backTicketBnt.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
