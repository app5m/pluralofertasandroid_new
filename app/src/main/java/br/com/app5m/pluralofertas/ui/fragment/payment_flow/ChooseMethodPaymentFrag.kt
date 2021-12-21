package br.com.app5m.pluralofertas.ui.fragment.payment_flow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.RequestControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.ui.activity.PaymentFlowContainerAct
import br.com.app5m.pluralofertas.ui.activity.SigininContentActivity
import br.com.app5m.pluralofertas.ui.activity.SucessAct
import br.com.app5m.pluralofertas.ui.fragment.payment_flow.cards.AddNewCardFrag
import br.com.app5m.pluralofertas.util.DialogMessages
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_add_new_card.*
import kotlinx.android.synthetic.main.fragment_choose_method_payment.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseMethodPaymentFrag : Fragment(), WSResult{

    private lateinit var useful: Useful
    private lateinit var requestControl: RequestControl
    private lateinit var preferences: Preferences

    private lateinit var paymentFlowContainerAct: PaymentFlowContainerAct

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
        preferences = Preferences(requireContext())
        requestControl = RequestControl(requireContext(), this, useful)

        paymentFlowContainerAct = requireActivity() as PaymentFlowContainerAct

        cardTv.setOnClickListener {

            paymentFlowContainerAct.fullDataPurchase.paymentForm = "1"
            useful.startFragmentOnBack(AddNewCardFrag(), requireActivity().supportFragmentManager)
        }


        ticketTv.setOnClickListener {
            paymentFlowContainerAct.fullDataPurchase.paymentForm = "2"
            useful.startFragmentOnBack(TicketMethodFrag(), requireActivity().supportFragmentManager)

        }


    }


}