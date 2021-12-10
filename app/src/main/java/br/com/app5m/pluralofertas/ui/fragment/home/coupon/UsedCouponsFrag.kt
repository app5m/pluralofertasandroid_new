package br.com.app5m.pluralofertas.ui.fragment.home.coupon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.CouponAdapter
import br.com.app5m.pluralofertas.adapter.UsedCouponsAdapter
import br.com.app5m.pluralofertas.controller.CouponControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Coupon
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.content_empty_list.*
import kotlinx.android.synthetic.main.content_sale_details.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_login_content.*
import kotlinx.android.synthetic.main.fragment_used_coupons.*
import kotlinx.android.synthetic.main.home_body.*
import java.util.ArrayList

class UsedCouponsFrag : Fragment(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var couponControl: CouponControl

    private val couponList = ArrayList<Coupon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
       return inflater.inflate(R.layout.fragment_used_coupons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        useful = Useful(requireContext())
        couponControl = CouponControl(requireContext(), this, useful)


        if (Preferences(requireContext()).getLogin()) {

            useful.openLoading()
            couponControl.listUsedCoupons()
        }

        configInitialViews()


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun cpResponse(list: List<Coupon>, type: String) {

        useful.closeLoading()

        couponList.clear()
        couponList.addAll(list)

        if (couponList[0].rows != "0") {
            usedCouponsRv.visibility = View.VISIBLE
            content_empty_list.visibility = View.GONE

            usedCouponsRv.adapter!!.notifyDataSetChanged()
        } else {
            content_empty_list.visibility = View.VISIBLE
            usedCouponsRv.visibility = View.GONE

        }

    }

    private fun configInitialViews(){

        val couponAdapter = UsedCouponsAdapter(requireContext(), couponList, this)

        usedCouponsRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = couponAdapter
        }

    }

}


