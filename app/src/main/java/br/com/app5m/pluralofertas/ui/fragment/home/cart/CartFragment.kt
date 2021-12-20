package br.com.app5m.pluralofertas.ui.fragment.home.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ItemsCartAdapter
import br.com.app5m.pluralofertas.controller.CartControl
import br.com.app5m.pluralofertas.controller.FreightControl
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.model.Freight
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.ui.activity.PaymentFlowContainerAct
import br.com.app5m.pluralofertas.ui.fragment.home.main.MyAddressesFragment
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.content_empty_list.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import java.util.ArrayList

class CartFragment : Fragment(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var cartControl: CartControl
    private lateinit var freightControl: FreightControl
    private lateinit var uAddressControl: UAddressControl

    private lateinit var preferences: Preferences

    private var cartList  = ArrayList<Cart>()

    private var globalFreightResponseInfo : Freight? = null
    private var globalIdCart : String? = null
    private var globalDestinyCep : String? = null
    private var globalCodFreight : String? = null

    var startedFullDataPurchase = Request()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireContext())
        useful = Useful(requireContext())
        cartControl = CartControl(requireContext(), this, useful)
        freightControl = FreightControl(requireContext(), this, useful)
        uAddressControl = UAddressControl(requireContext(), this, useful)

        configureInitialViews()

    }


    override fun uAResponse(list: List<UAddress>, type: String) {

        useful.closeLoading()

        val responseInfo = list

        address_sp.visibility = View.VISIBLE

        val spinnerArrayGroup: MutableList<String?> = ArrayList()

        spinnerArrayGroup.add("Selecione o endereço")

        if (!responseInfo[0].rows.equals("0")) {
            for (i in responseInfo.indices) {
                spinnerArrayGroup.add(
                    responseInfo[i].city.toString() + ", " + responseInfo[i].neighborhood
                )
            }
        }

        val adapter: ArrayAdapter<*> = ArrayAdapter<String?>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArrayGroup)

        address_sp.adapter = adapter

        address_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                if (position == 0) {
                    globalDestinyCep = null
                    globalCodFreight = null
                    startedFullDataPurchase.idAddress = null

                } else {
                    globalDestinyCep = responseInfo[position - 1].cep!!
                    startedFullDataPurchase.idAddress = responseInfo[position - 1].id!!
                }



            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        globalIdCart?.let { cartControl.listItems(it) }


    }

    override fun fResponse(list: List<Freight>, type: String) {


        globalFreightResponseInfo = list[0]

        if (globalFreightResponseInfo!!.cService!!.status == "01") {

            valueFreightTv.text = "R$ " + globalFreightResponseInfo!!.cService!!.value

            cartControl.listItems(globalIdCart!!)
//
//        "cServico": {
//            "Codigo": "40010",
//            "Valor": "33,58",
//            "PrazoEntrega": "1",
//            "ValorSemAdicionais": "22,50",
//            "ValorMaoPropria": "7,50",
//            "ValorAvisoRecebimento": "0,00",
//            "ValorValorDeclarado": "3,58",
//            "EntregaDomiciliar": "S",
//            "EntregaSabado": "N",
//            "obsFim": {},
//            "Erro": "0",
//            "MsgErro": {},
//            "status": "01",
//            "msg": "Ok"
//        }

        } else {
            globalIdCart?.let { cartControl.listItems(it) }
            SingleToast.INSTANCE.show(requireContext(), globalFreightResponseInfo!!.cService!!.msg!!, Toast.LENGTH_SHORT)
        }


    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun cResponse(list: List<Cart>, type: String) {

        val responseInfo = list[0]

        if (type == "listItems") {

            useful.closeLoading()

            //
//        [
//            {
//                "id_item": 10,
//                "id_oferta": 3,
//                "nome_oferta": "Testando",
//                "valor_uni": " R$ 189,00",
//                "qtd": 1,
//                "valor_itens": " R$ 194,00",
//                "valor_derivado": " R$ 30,00",
//                "valor_desconto": " R$ 25,00",
//                "valor_final": " R$ 199,00",
//                "valor_descontado_float": 199,
//                "id_derivado": 3,
//                "nome_derivado": "Rota adicional",
//                "peso": "1",
//                "altura": 1,
//                "largura": 1,
//                "comprimento": 1,
//                "derivados": [
//                {
//                    "rows": 0
//                }
//                ]
//            },
//            {
//                "total_carrinho": " R$ 199,00"
//            }
//        ]
//

            cartList.clear()

            for (item in list) {
                if (item.idItem != null) {
                    cartList.add(item)
                }
            }

            if (responseInfo.rows != "0") {
                cartCons.visibility = View.VISIBLE
                content_empty_list.visibility = View.GONE

                cartRv.adapter!!.notifyDataSetChanged()
            } else {
                content_empty_list.visibility = View.VISIBLE
                cartCons.visibility = View.GONE

                return
            }

            //pedir pro diogo
            if (responseInfo.type == "Entrega") {
                freight_ll.visibility = View.VISIBLE
            }

            subtotalTv.text = responseInfo.finalValue

            startedFullDataPurchase.subTotalValue = responseInfo.finalValue

            if (globalFreightResponseInfo != null){
                val total = useful.moneyToDouble(globalFreightResponseInfo!!.cService!!.value!!.replace(".", "")) +
                        useful.moneyToDouble(responseInfo.finalValue!!.replace(".", ""))

                totalTv.text = "R$ " + total.toString().replace(".", ",")

            }

            //loadcart
        } else if (type == "loadCart") {
            if (responseInfo.cartOpen == null) {

                useful.closeLoading()

                content_empty_list.visibility = View.VISIBLE
                cartCons.visibility = View.GONE
            } else {

                globalIdCart = responseInfo.cartOpen!!

                uAddressControl.findAddress()


            }

        } else {


            if (responseInfo.status != "01") {
                startedFullDataPurchase.descValueCoupon = null
                startedFullDataPurchase.idCoupon = null
            }

            globalIdCart?.let { cartControl.listItems(it) }

        }


    }



    private fun configureInitialViews(){

        val cartItemsAdapter = ItemsCartAdapter(requireContext(), cartList, useful, this, this)

        cartRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }

        paymentBtn.setOnClickListener {

            if (globalDestinyCep == null || globalCodFreight == null) {

                SingleToast.INSTANCE.show(requireContext(),
                    "É necessário escolher seu método de entrega antes de prosseguir com o pagamento", Toast.LENGTH_LONG)
                return@setOnClickListener
            }

            startedFullDataPurchase.idCart = globalIdCart
            startedFullDataPurchase.freightValue = globalFreightResponseInfo!!.cService!!.value

            startActivity(Intent(requireContext(), PaymentFlowContainerAct::class.java).
                putExtra("full", startedFullDataPurchase))

        }

        freight_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (globalDestinyCep == null) {

                    SingleToast.INSTANCE.show(requireContext(), "É necessário escolher seu endereço antes", Toast.LENGTH_SHORT)
                    return
                }

                val freight = Freight()

                when (position) {
                    0 -> {

                        //sedex
                        globalCodFreight = "40010"

                        startedFullDataPurchase.idFreight = "1"
                    }
                    1 -> {
                        //pac ver codigo pac dps
                        globalCodFreight = "41106"

                        startedFullDataPurchase.idFreight = "2"
                    }
                    else -> {
                        return
                    }
                }

                freight.destinyCep = globalDestinyCep
                freight.cartId = globalIdCart
                freight.cod = globalCodFreight

                freightControl.estimateFreight(freight)


            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        if (Preferences(requireContext()).getLogin()) {

            useful.openLoading()
            cartControl.loadCart()

        } else {
            cartCons.visibility = View.GONE
            content_empty_list.visibility = View.VISIBLE
        }


    }
}