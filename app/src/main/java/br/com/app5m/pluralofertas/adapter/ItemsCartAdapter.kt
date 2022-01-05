package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.CartControl
import br.com.app5m.pluralofertas.controller.CouponControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.model.Coupon
import br.com.app5m.pluralofertas.ui.activity.SigininContentActivity
import br.com.app5m.pluralofertas.ui.fragment.home.cart.CartFragment
import br.com.app5m.pluralofertas.util.DialogMessages
import br.com.app5m.pluralofertas.util.Useful

class ItemsCartAdapter (private val context: Context, private val list: List<Cart>, private val useful: Useful,
                        private val wsResult: WSResult, private val cartFragment: CartFragment)
    : RecyclerView.Adapter<ItemsCartAdapter.ViewHolder>() {

    var isAdded = false

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
        val saleValueTv: TextView = itemView.findViewById(R.id.value_tv)
        val removeIb: ImageButton = itemView.findViewById(R.id.remove_ib)
        val couponsRv: RecyclerView = itemView.findViewById(R.id.couponsRv)
        val couponsLL: LinearLayout = itemView.findViewById(R.id.couponsLl)
        val subTitleCouponTv: TextView = itemView.findViewById(R.id.subTitleCoupon_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_items_cart, parent, false)
        return ViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cart = list[position]

        holder.nameTv.text = cart.saleName
        holder.saleValueTv.text = cart.unityValue

        cart.idSale.let {
            if (it != null) {
                CouponControl(context, object : WSResult {
                    override fun cpResponse(list: List<Coupon>, type: String) {

                        val responseInfo = list[0]

                        if (responseInfo.rows != "0") {
                            val couponAdapter = CouponAdapter(context, list, object : RecyclerItemClickListener{
                                override fun onClickListenerCoupon(coupon: Coupon) {

                                    val addCoupon = Cart()

                                    addCoupon.cod = coupon.cod
                                    addCoupon.idItem = cart.idItem
                                    addCoupon.descValue = coupon.descValue

                                    CartControl(context, wsResult, useful).addCoupon(addCoupon)

                                    //por enquanto deixa aqui
                                    cartFragment.startedFullDataPurchase.descValueCoupon = coupon.descValue
                                    cartFragment.startedFullDataPurchase.idCoupon = coupon.id

                                }
                            } )

                            holder.couponsRv.apply {
                                setHasFixedSize(false)
                                layoutManager = LinearLayoutManager(context)
                                adapter = couponAdapter
                            }

                            holder.couponsLL.visibility = View.VISIBLE

                            if (isAdded) {
                                holder.couponsRv.visibility = View.GONE

                                holder.subTitleCouponTv.text = "Um cupom de desconto foi adicionado para esta oferta."

                            } else {

                                holder.couponsRv.visibility = View.VISIBLE

                                holder.subTitleCouponTv.text = "Cupons para esta oferta:"

                            }
                        } else {
                            holder.couponsLL.visibility = View.GONE
                        }
                    }
                }, useful).listCoupons(it)
            }
        }

        holder.removeIb.setOnClickListener {

            DialogMessages(context).click("Atenção",
                "Você tem certeza que deseja retirar este item do seu carrinho?",
                object : DialogMessages.Answer {
                    override fun setOnClickListener() {

                        CartControl(context, wsResult, useful).removeItem(cart.idItem!!)
                    }
                })

        }

        //COLOCAR MSG QUE CUPOM JA FOI ADD E ESCONDER LL

    }

    override fun getItemCount(): Int {
        return list.size
    }
}