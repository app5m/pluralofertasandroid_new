package br.com.app5m.pluralofertas.ui.fragment.home.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.ItemsCartAdapter
import br.com.app5m.pluralofertas.controller.CartControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.content_empty_list.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import java.util.ArrayList

class CartFragment : Fragment(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var cartControl: CartControl

    private var cartList  = ArrayList<Cart>()

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

        useful.openLoading()
        cartControl.listItems()

        configureInitialViews()

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
                emptyContent.visibility = View.GONE
            } else {
                emptyContent.visibility = View.VISIBLE
                cartCons.visibility = View.GONE
            }

            cartRv.adapter!!.notifyDataSetChanged()
        }


    }

    private fun configureInitialViews(){

        val cartItemsAdapter = ItemsCartAdapter(requireContext(), cartList,this)

        cartRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }

    }
}