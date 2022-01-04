package br.com.app5m.pluralofertas.ui.fragment.payment_flow

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
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
import br.com.app5m.pluralofertas.ui.activity.SucessAct
import br.com.app5m.pluralofertas.ui.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.util.*
import kotlinx.android.synthetic.main.fragment_add_new_card.*
import kotlinx.android.synthetic.main.fragment_ticket_method.*
import kotlinx.android.synthetic.main.fragment_ticket_method.address_tv
import kotlinx.android.synthetic.main.fragment_ticket_method.birth_et
import kotlinx.android.synthetic.main.fragment_ticket_method.cellphone_et
import kotlinx.android.synthetic.main.fragment_ticket_method.document_et
import kotlinx.android.synthetic.main.fragment_ticket_method.finish_bt
import kotlinx.android.synthetic.main.fragment_ticket_method.name_et

/**
 * A simple [Fragment] subclass.
 */
class TicketMethodFrag : Fragment(), WSResult {

    private lateinit var useful: Useful
    private lateinit var requestControl: RequestControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private lateinit var cpfMask: TextWatcher
    private lateinit var cnpjMask: TextWatcher

    private lateinit var paymentFlowContainerAct: PaymentFlowContainerAct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        requestControl = RequestControl(requireContext(), this, useful)

        if(preferences.getUAddressData() != null) {

            address_tv.text = preferences.getUAddressData()!!.address + " - " + preferences.getUAddressData()!!.number

        }

        paymentFlowContainerAct = requireActivity() as PaymentFlowContainerAct

        finish_bt.setOnClickListener {

            if (!validate()) return@setOnClickListener

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

                        if(preferences.getUAddressData() != null) {
                            newRequest.cardCep = preferences.getUAddressData()!!.cep
                            newRequest.cardState = preferences.getUAddressData()!!.state
                            newRequest.cardCity = preferences.getUAddressData()!!.city
                            newRequest.cardNeighborhood = preferences.getUAddressData()!!.neighborhood
                            newRequest.cardAddress = preferences.getUAddressData()!!.address
                            newRequest.cardNumber = preferences.getUAddressData()!!.number
                            newRequest.cardComplement = preferences.getUAddressData()!!.complement
                        } else {
                            SingleToast.INSTANCE.show(requireContext(),
                                "Por favor, insira o endereço da cobrança.",
                                Toast.LENGTH_LONG)
                            return
                        }

                        newRequest.cardName = name_et.text.toString()
                        newRequest.cardCellphone = cellphone_et.text.toString()
                        newRequest.cardCpf = document_et.text.toString()
                        newRequest.cardBirth = birth_et.text.toString()
                        newRequest.cpf = document_et.text.toString()

//                          hash
//                        newRequest.installments = "1"
                        newRequest.plataform = "1"


                        requestControl.newRequest(newRequest)

                    }
                })
        }

        address_tv.setOnClickListener {
            val dialog = RegisterAddressDialog()
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager,"DialogRegisterAddress")
        }

        loadMasks()

    }

    override fun onActivityResult(requestCode: Int, resultCode2: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode2, data)

        if (data!!.extras!!.getBoolean("msg")) {
            address_tv.text = preferences.getUAddressData()!!.address + " - " + preferences.getUAddressData()!!.number

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

    private fun loadMasks() {
        cpfMask = Mask.insert("###.###.###-##", document_et)
        cnpjMask = Mask.insert("##.###.###/####-##", document_et)

        document_et.addTextChangedListener(cpfMask)
    }

    private fun validate() : Boolean {
        if (!validation.name(name_et)) return false
        if (!validation.date(birth_et)) return false
/*

        if (document_et.text.length > 13) {
*/

            if (!validation.cpf(document_et)) return false
/*

        } else if (document_et.text.length < 14) {
            if (!validation.cnpj(document_et)) return false
        }
*/

        if (!validation.cellphone(cellphone_et)) return false

        return true
    }
}