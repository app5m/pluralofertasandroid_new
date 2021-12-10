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
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.model.Derivative
import br.com.app5m.pluralofertas.model.Photo
import br.com.app5m.pluralofertas.model.Sale
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


class SaleDetailsActivity : AppCompatActivity(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var saleControl: SaleControl
    private lateinit var cartControl: CartControl

    private lateinit var globalResponseSaleInfo: Sale
    private var globalDerivative = Derivative()

    private var fragmentList = ArrayList<Fragment>()

    private val handler = Handler()
    private var imageRunnable = Runnable { showAnimate() }

    private inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            // Show dynamic total pages.
            return fragmentList.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_sale)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        saleControl = SaleControl(this, this, useful)
        cartControl = CartControl(this, this, useful)

        supportActionBar?.let { useful.setActionBar(this, it, "", 0) }

        if (intent.extras != null) {
            useful.openLoading()
            intent.getStringExtra("idSale")?.let { saleControl.listIdSales(it) }
        }


        back_ib.setOnClickListener {
            onBackPressed()
        }

        buyBt.setOnClickListener {

            if (!Preferences(this).getLogin()) {
                SingleToast.INSTANCE.show(this, "É necessário efetuar seu login para poder realizar compras!", Toast.LENGTH_LONG)
                return@setOnClickListener
            }

            val newItem = Cart()

            newItem.idSale = globalResponseSaleInfo.details!!.id
            newItem.unityValue = globalResponseSaleInfo.details!!.value
            newItem.servicePrice = globalResponseSaleInfo.details!!.servicePrice
            newItem.idDerivative = globalDerivative.id

            useful.openLoading()
            cartControl.addItem(newItem)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(imageRunnable)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun sResponse(list: List<Sale>, type: String) {

        useful.closeLoading()

        globalResponseSaleInfo = list[0]

        saleNameTv.text = globalResponseSaleInfo.details!!.name
        typeTv.text = globalResponseSaleInfo.details!!.type
        saleValueTv.text = globalResponseSaleInfo.details!!.value

        val derivativesAdapter =
            globalResponseSaleInfo.derivativeList?.let {
                DerivativesAdapter(this,
                    it, this)
            }

        derivativesRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@SaleDetailsActivity)
            adapter = derivativesAdapter
        }

        if (globalResponseSaleInfo.photoList?.get(0)!!.rows.equals("0")) return

        //forzinho padrao pra ver quantos rows tem no vagaubudo
        for (item: Photo in globalResponseSaleInfo.photoList!!) {

            fragmentList.add(PhotoContainerFrag(item.url!!))

        }

        loadContainerPhotos()
//        [
//            {
//                "detalhes": {
//                "id": 1,
//                "nome": "PASSAPORTE Dreamland",
//                "tipo": "Entrega",
//                "valor": " R$ 189,00",
//                "taxa_servico": " R$ 25,00",
//                "capa": "empty.png"
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
//                    "id": 2,
//                    "url": "7302-6c4039ccf3ad486471ec6676fd9ea250.png",
//                    "rows": 3
//                },
//                {
//                    "id": 3,
//                    "url": "1669-93bdd44d384d8715e20554952b49ab07.png",
//                    "rows": 3
//                },
//                {
//                    "id": 5,
//                    "url": "5657-b9dead9bfc52b393e42d9832f0f2a7f7.jpeg",
//                    "rows": 3
//                }
//                ],
//                "derivados": [
//                {
//                    "id": 3,
//                    "nome": "Rota adicional",
//                    "descricao": "extensão do passeio",
//                    "valor": " R$ 30,00",
//                    "rows": 1
//                }
//                ]
//            }
//        ]


    }

    override fun cResponse(list: List<Cart>, type: String) {

        useful.closeLoading()

        val responseInfo = list[0]

        if (responseInfo.status == "01") {

            val broadcastManager = LocalBroadcastManager.getInstance(this)
            val intentBroadcast = Intent("Notification")
            intentBroadcast.putExtra("order", "ADD_TO_CART")
            broadcastManager.sendBroadcast(intentBroadcast)

            finish()
        } else {
            SingleToast.INSTANCE.show(this, responseInfo.msg!!, Toast.LENGTH_LONG)
        }


    }

    override fun onClickListenerDerivative(derivative: Derivative?) {

        if (derivative != null) {
            globalDerivative = derivative
        }

    }

    private fun showAnimate() {

        if (containerPhotosPager.currentItem == fragmentList.size - 1) {

            containerPhotosPager.currentItem = 0
        } else {

            containerPhotosPager.currentItem++
        }

        handler.postDelayed(imageRunnable, (7 * 1000).toLong())

    }


    private fun loadContainerPhotos() {

        val adapter = SectionsPagerAdapter(supportFragmentManager)

        containerPhotosPager.adapter = adapter

        indicator.setViewPager(containerPhotosPager)

        containerPhotosPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        handler.postDelayed(imageRunnable, (7 * 1000).toLong())

    }


}