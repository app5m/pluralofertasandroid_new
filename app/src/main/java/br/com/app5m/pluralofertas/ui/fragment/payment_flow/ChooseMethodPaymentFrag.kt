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

            DialogMessages(requireContext()).click("Gerar boleto",
                "Clique em confirmar para finalizar sua compra via boleto bancário!",
                object : DialogMessages.Answer {
                    override fun setOnClickListener() {

                        useful.openLoading()

                        val newRequest = Request()

                        newRequest.idCart = paymentFlowContainerAct.fullDataPurchase.idCart
                        newRequest.idAddress = paymentFlowContainerAct.fullDataPurchase.idAddress
                        newRequest.paymentForm = paymentFlowContainerAct.fullDataPurchase.paymentForm

                        newRequest.idFreight = paymentFlowContainerAct.fullDataPurchase.idFreight
                        newRequest.freightValue = paymentFlowContainerAct.fullDataPurchase.freightValue
                        newRequest.subTotalValue = paymentFlowContainerAct.fullDataPurchase.subTotalValue

                        newRequest.typeDelivery = paymentFlowContainerAct.fullDataPurchase.typeDelivery

                        newRequest.descValueCoupon = paymentFlowContainerAct.fullDataPurchase.descValueCoupon
                        newRequest.idCoupon = paymentFlowContainerAct.fullDataPurchase.idCoupon

                        newRequest.obs = "nenhuma"

                        newRequest.cardNumber = "80"
                        newRequest.cardCep = "91250310"
                        newRequest.cardState = "RS"
                        newRequest.cardCity = "Porto Alegre"
                        newRequest.cardNeighborhood = "Rubem Berta"
                        newRequest.cardAddress = "Avenida adelino"
                        newRequest.cardComplement = "complemento"

                        newRequest.cardName = "jubirildo"
                        newRequest.cardCellphone = "54354364564"
                        newRequest.cardCpf = "54356554332"
                        newRequest.cpf = "54356554332"
                        newRequest.cardBirth = "10/10/2000"

                        newRequest.installments = "1"
                        newRequest.plataform = "1"


                        requestControl.newRequest(newRequest)

                    }
                })
        }


    }

    override fun rResponse(list: List<Request>, type: String) {

        useful.closeLoading()

        val responseInfo = list[0]

        if (responseInfo.status == "01") {

            startActivity(Intent(requireContext(), SucessAct::class.java).putExtra("ticket", responseInfo.ticketLink))
            requireActivity().finishAffinity()

        }

        SingleToast.INSTANCE.show(requireActivity(), responseInfo.msg!!, Toast.LENGTH_LONG)

    }

}