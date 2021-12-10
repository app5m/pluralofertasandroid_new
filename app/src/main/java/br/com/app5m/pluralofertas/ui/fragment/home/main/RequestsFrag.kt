package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.RequestAdapter
import br.com.app5m.pluralofertas.controller.RequestControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.ui.activity.RequestDetailsActivity
import br.com.app5m.pluralofertas.ui.activity.SaleDetailsActivity
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.content_empty_list.*
import kotlinx.android.synthetic.main.fragment_requests.*
import kotlinx.android.synthetic.main.fragment_used_coupons.*
import java.util.ArrayList

class RequestsFrag: Fragment(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var requestControl: RequestControl

    private val requestList = ArrayList<Request>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        requestControl = RequestControl(requireContext(), this, useful)

        useful.openLoading()
        requestControl.find()

        configureInitialViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun rResponse(list: List<Request>, type: String) {

        useful.closeLoading()

        requestList.clear()

        if (requestList[0].rows != "0") {
            requestList.addAll(list)
            requestsRv.visibility = View.VISIBLE
            content_empty_list.visibility = View.GONE

            requestsRv.adapter!!.notifyDataSetChanged()
        } else {
            content_empty_list.visibility = View.VISIBLE
            requestsRv.visibility = View.GONE

        }

    }

    override fun onClickListenerRequest(request: Request) {

        val intent = Intent(requireContext(), RequestDetailsActivity::class.java)
        intent.putExtra("idRequest", request.id)
        startActivity(intent)

    }

    private fun configureInitialViews(){

        val requestAdapter = RequestAdapter(requireContext(), requestList,this)

        requestsRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }
    }

}