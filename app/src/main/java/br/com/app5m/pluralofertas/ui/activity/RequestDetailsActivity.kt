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

        supportActionBar?.let { useful.setActionBar(this, it, "", 0) }

        if (intent.extras != null) {
            useful.openLoading()
            intent.getStringExtra("idRequest")?.let { requestControl.findId(it) }
        }



    }


    override fun rResponse(list: List<Request>, type: String) {

        useful.closeLoading()
    }


}