package br.com.app5m.pluralofertas.ui.fragment.home.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ItemsCartAdapter
import br.com.app5m.pluralofertas.controller.CartControl
import br.com.app5m.pluralofertas.controller.FreightControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.model.Freight
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

    private var cartList  = ArrayList<Cart>()

    private lateinit var globalFreightResponseInfo : Freight

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        cartControl = CartControl(requireContext(), this, useful)
        freightControl = FreightControl(requireContext(), this, useful)

        if (Preferences(requireContext()).getLogin()) {

            useful.openLoading()
            cartControl.listItems("6")

        } else {
            cartCons.visibility = View.GONE
            content_empty_list.visibility = View.VISIBLE
        }

        configureInitialViews()

    }

    override fun fResponse(list: List<Freight>, type: String) {


        globalFreightResponseInfo = list[0]

        if (globalFreightResponseInfo.cService!!.status == "01") {

            valueFreightTv.text = ""
            subtotalTv.text = ""
            totalTv.text = ""

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
            SingleToast.INSTANCE.show(requireContext(), "", Toast.LENGTH_SHORT)
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun cResponse(list: List<Cart>, type: String) {

        useful.closeLoading()

        val responseInfo = list[0]

        if (type == "listItems") {

            cartList.clear()

            if (responseInfo.rows != "0") {
                cartList.addAll(list)
                cartCons.visibility = View.VISIBLE
                content_empty_list.visibility = View.GONE
            } else {
                content_empty_list.visibility = View.VISIBLE
                cartCons.visibility = View.GONE
            }

            cartRv.adapter!!.notifyDataSetChanged()
        }

        freight_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val freight = Freight()

                freight.destinyCep = "94836000"
                freight.cartId = "6"

                when (position) {
                    1 -> {

                        //pac ver codigo pac dps
                        freight.cod = "40010"

                    }
                    2 -> {
                        //sedex
                        freight.cod = "40010"
                    }
                    else -> {
                        return
                    }
                }

                freightControl.estimateFreight(freight)


            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }



    private fun configureInitialViews(){

        val cartItemsAdapter = ItemsCartAdapter(requireContext(), cartList,this)

        cartRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }

        paymentBtn.setOnClickListener {

            startActivity(Intent(requireContext(), PaymentFlowContainerAct::class.java).putExtra("idCart", ""))

        }

        addressTv.setOnClickListener {
            useful.startFragmentOnBack(MyAddressesFragment(), requireActivity().supportFragmentManager)
        }

    }
}