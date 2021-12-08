package br.com.app5m.pluralofertas.ui.activity


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.toolbar.*


class SaleDetailsActivity : AppCompatActivity(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var saleControl: SaleControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_sale)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        saleControl = SaleControl(this, this, useful)

        supportActionBar?.let { useful.setActionBar(this, it,"", 0) }

        if (intent.extras != null) {
            useful.openLoading()
            intent.getStringExtra("idSale")?.let { saleControl.listIdSales(it) }
        }

    }

    override fun sResponse(list: List<Sale>, type: String) {

        useful.closeLoading()

//        [
//            {
//                "detalhes": {
//                "id": 3,
//                "nome": "Testando",
//                "tipo": "Entrega",
//                "valor": " R$ 12,00",
//                "taxa_servico": " R$ 25,00",
//                "capa": "4878-f34502796d66877a6962afa6e5daa1d2.jpg"
//            },
//                "categorias": [
//                {
//                    "id": 1,
//                    "nome": "Passeio + Hospedagem",
//                    "rows": 1
//                }
//                ],
//                "fotos": [
//                {
//                    "rows": 0
//                }
//                ],
//                "derivados": [
//                {
//                    "rows": 0
//                }
//                ]
//            }
//        ]

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}