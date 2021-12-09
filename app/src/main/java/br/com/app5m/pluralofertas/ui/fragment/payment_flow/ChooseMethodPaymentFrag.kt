package br.com.app5m.pluralofertas.ui.fragment.payment_flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.fragment.payment_flow.cards.AddNewCardFrag
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_choose_method_payment.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseMethodPaymentFrag : Fragment() {

    private lateinit var useful: Useful

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_method_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())

        ticketTv.setOnClickListener {

            useful.startFragmentOnBack(TicketMethodFrag(), requireActivity().supportFragmentManager)
        }

        cardTv.setOnClickListener {


            useful.startFragmentOnBack(AddNewCardFrag(), requireActivity().supportFragmentManager)
        }

    }

}