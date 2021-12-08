package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.activity.SaleDetailsActivity
import br.com.app5m.pluralofertas.adapter.HighlightsAdapter
import br.com.app5m.pluralofertas.adapter.SalesAdapter
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.CircleRecyclerViewDecoration
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Highlight
import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.home_body.*
import java.util.*

class HomeFragmentOffer : Fragment(), RecyclerItemClickListener, WSResult {

    private var saleList = ArrayList<Sale>()
    private val highlightsList = ArrayList<Highlight>()

    private lateinit var useful: Useful
    private lateinit var saleControl: SaleControl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_home, container, false)

//            val dialog = FilterDialog()
//            dialog.setTargetFragment(this, 1)
//            fragmentManager?.let { it1 -> dialog.show(it1,"FilterDialog") }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        saleControl = SaleControl(requireContext(), this, useful)

        saleControl.findSale("2")

        highlightsList.add(Highlight())
        highlightsList.add(Highlight())

        configureInitialViews()

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun sResponse(list: List<Sale>, type: String) {

        useful.closeLoading()

        saleList.clear()
        saleList.addAll(list)

        salesRv.adapter!!.notifyDataSetChanged()

    }

    private fun configureInitialViews(){

        val highlightsAdapter = HighlightsAdapter(requireContext(), highlightsList, this)

        highlightsRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = highlightsAdapter
        }

        highlightsRv.addItemDecoration(CircleRecyclerViewDecoration())

        val salesAdapter = SalesAdapter(requireContext(), saleList,this)

        salesRv.apply {
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = salesAdapter
        }
    }

    override fun onClickListenerSale(sale: Sale) {

        val intent = Intent(activity, SaleDetailsActivity::class.java)
        intent.putExtra("idSale", sale.id)
        startActivity(intent)

    }

    override fun onClickListenerHighlight(highlight: Highlight) {
//        val intent = Intent(activity, SaleDetailsActivity::class.java)
//        intent.putExtra("highlight", highlight)
//        startActivity(intent)

    }
}

