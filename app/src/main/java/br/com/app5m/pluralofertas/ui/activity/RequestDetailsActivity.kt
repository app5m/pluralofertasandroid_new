package br.com.app5m.pluralofertas.ui.activity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.DerivativesAdapter
import br.com.app5m.pluralofertas.adapter.UAddressAdapter
import br.com.app5m.pluralofertas.controller.CartControl
import br.com.app5m.pluralofertas.controller.RequestControl
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.*
import br.com.app5m.pluralofertas.ui.fragment.PhotoContainerFrag
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.activity_details_sale.*
import kotlinx.android.synthetic.main.content_empty_list.*
import kotlinx.android.synthetic.main.content_sale_details.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import kotlinx.android.synthetic.main.toolbar.*


class RequestDetailsActivity : AppCompatActivity(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var requestControl: RequestControl

    private lateinit var globalResponseSaleInfo: Sale
    private var globalDerivative = Derivative()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_request)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        requestControl = RequestControl(this, this, useful)

        supportActionBar?.let { useful.setActionBar(this, it, "Detalhes do pedido", 0) }

        if (intent.extras != null) {
            useful.openLoading()
            intent.getStringExtra("idRequest")?.let { requestControl.findId(it) }
        }
//
//        [
//            {
//                "id": 14,
//                "tipo_pagamento": "Boleto",
//                "tipo_entrega": "Retirada",
//                "valor_total": " R$ 194,00",
//                "data": "10/11/2021 - 21:24:11",
//                "status_pedido": "Em separação",
//                "oferta": "Testando",
//                "status_pagamento": "AUTORIZADO",


//                "frete": "PAC",
//                "cep": " 94818-47",
//                "cidade": "Alvorada",
//                "endereco": "R. Martinho Lutero",
//                "numero": "",
//                "bairro": " Formoza",
//                "complemento": "",
//                "valor_frete": " R$ 20,00",

//                "valor_desc": " R$ 25,00",
//                "valor_subtotal": " R$ 209,00",
//                "voucher": "y1N37uU1",
//                "cupom": "swTWan3p",
//                "url": "4878-f34502796d66877a6962afa6e5daa1d2.jpg",

//                "id_derivado": 3,
//                "nome_derivado": "Rota adicional",
//                "descricao_derivado": "extensão do passeio",
//                "valor_derivado": " R$ 30,00",
//            }


    }


    override fun rResponse(list: List<Request>, type: String) {

        useful.closeLoading()
    }


}