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

    private var globalCartResponseInfo : Cart? = null
    private var globalFreightResponseInfo : Freight? = null
    private var globalIdCart : String? = null
    private var globalDestinyCep : String? = null
    private var globalCodFreight : String? = null

    private lateinit var cartItemsAdapter: ItemsCartAdapter

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

        } else {
            globalIdCart?.let { cartControl.listItems(it) }
            SingleToast.INSTANCE.show(requireContext(), globalFreightResponseInfo!!.cService!!.msg!!, Toast.LENGTH_SHORT)
        }


    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun cResponse(list: List<Cart>, type: String) {

        globalCartResponseInfo = list[0]

        if (type == "listItems") {

            useful.closeLoading()

            cartList.clear()

            for (item in list) {
                if (item.idItem != null) {
                    cartList.add(item)
                }
            }

            if (globalCartResponseInfo!!.rows != "0") {
                cartCons.visibility = View.VISIBLE
                content_empty_list.visibility = View.GONE

                if (globalCartResponseInfo!!.descValue != "R$ 0,00") {
                    cartItemsAdapter.isAdded = true
                }

                cartRv.adapter!!.notifyDataSetChanged()
            } else {
                content_empty_list.visibility = View.VISIBLE
                cartCons.visibility = View.GONE

                return
            }

            if (globalCartResponseInfo!!.typeDelivery == "1") {
                freight_ll.visibility = View.VISIBLE
            }

            if (globalFreightResponseInfo != null){

                val total = useful.moneyToDouble(globalFreightResponseInfo!!.cService!!.value!!.replace(".", "")) +
                        useful.moneyToDouble(globalCartResponseInfo!!.finalValue!!.replace(".", ""))

                subtotalTv.text = globalCartResponseInfo!!.finalValue
                totalTv.text = "R$ " + total.toString().replace(".", ",")

            } else {

                totalTv.text = globalCartResponseInfo!!.finalValue
            }

            startedFullDataPurchase.subTotalValue = globalCartResponseInfo!!.finalValue

            //loadcart
        } else if (type == "loadCart") {
            if (globalCartResponseInfo!!.cartOpen == null) {

                useful.closeLoading()

                content_empty_list.visibility = View.VISIBLE
                cartCons.visibility = View.GONE
            } else {

                globalIdCart = globalCartResponseInfo!!.cartOpen!!

                uAddressControl.findAddress()


            }

        } else {


            if (globalCartResponseInfo!!.status != "01") {
                startedFullDataPurchase.descValueCoupon = null
                startedFullDataPurchase.idCoupon = null
            }

            globalIdCart?.let { cartControl.listItems(it) }

        }


    }



    private fun configureInitialViews(){

        cartItemsAdapter = ItemsCartAdapter(requireContext(), cartList, useful, this, this)

        cartRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }

        paymentBtn.setOnClickListener {

            if (globalCartResponseInfo!!.typeDelivery == "1") {
                if (globalDestinyCep == null || globalCodFreight == null) {

                    SingleToast.INSTANCE.show(requireContext(),
                        "É necessário escolher seu método de entrega antes de prosseguir com o pagamento", Toast.LENGTH_LONG)
                    return@setOnClickListener
                }

                startedFullDataPurchase.freightValue = globalFreightResponseInfo!!.cService!!.value
            }

            startedFullDataPurchase.idCart = globalIdCart
            startedFullDataPurchase.typeDelivery = globalCartResponseInfo!!.typeDelivery

            startActivity(Intent(requireContext(), PaymentFlowContainerAct::class.java).
            putExtra("full", startedFullDataPurchase))

        }

        freight_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (globalDestinyCep == null) {

                    SingleToast.INSTANCE.show(requireContext(), "É necessário escolher seu endereço antes", Toast.LENGTH_SHORT)
                    return
                }

                when (position) {
                    1 -> {

                        //sedex
                        globalCodFreight = "40010"

                        startedFullDataPurchase.idFreight = "1"
                    }
                    2 -> {
                        //pac ver codigo pac dps
                        globalCodFreight = "41106"

                        startedFullDataPurchase.idFreight = "2"
                    }
                    else -> {
                        globalCodFreight = null
                        return
                    }
                }

                val freight = Freight()

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