package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
import br.com.app5m.pluralofertas.util.DialogMessages
import br.com.app5m.pluralofertas.util.Useful

class ItemsCartAdapter (private val context: Context, private val list: List<Cart>, private val useful: Useful,
                        private val wsResult: WSResult)
    : RecyclerView.Adapter<ItemsCartAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
        val saleValueTv: TextView = itemView.findViewById(R.id.value_tv)
        val removeIb: ImageButton = itemView.findViewById(R.id.remove_ib)
        val couponsRv: RecyclerView = itemView.findViewById(R.id.couponsRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_items_cart,
                parent,
                false
            ) // vai conectar com os ids abaixo
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

                        if (responseInfo.status != "0") {
                            val couponAdapter = CouponAdapter(context, list, object : RecyclerItemClickListener{
                                override fun onClickListenerCoupon(coupon: Coupon) {

                                    val addCoupon = Cart()

                                    addCoupon.cod = coupon.cod
                                    addCoupon.idItem = cart.idItem
                                    addCoupon.descValue = coupon.descValue

                                    CartControl(context, wsResult, useful).addCoupon(addCoupon)

                                }
                            } )

                            holder.couponsRv.apply {
                                setHasFixedSize(false)
                                layoutManager = LinearLayoutManager(context)
                                adapter = couponAdapter
                            }
                        }
                    }
                }, useful).listCoupons(it)
            }
        }



//            {
//                "id_item": 10,
//                "id_oferta": 3,
//                "nome_oferta": "Testando",
//                "valor_uni": " R$ 189,00",
//                "qtd": 1,
//                "valor_itens": " R$ 194,00",
//                "valor_derivado": " R$ 30,00",
//                "valor_desconto": " R$ 25,00",
//                "valor_final": " R$ 199,00",
//                "valor_descontado_float": 199,
//                "id_derivado": 3,
//                "nome_derivado": "Rota adicional",
//                "peso": "1",
//                "altura": 1,
//                "largura": 1,
//                "comprimento": 1,
//                "derivados": [
//                {
//                    "rows": 0
//                }
//                ]
//            },
//            {
//                "total_carrinho": " R$ 199,00"
//            }

        holder.removeIb.setOnClickListener {

            DialogMessages(context).click("Atenção",
                "Você tem certeza que deseja retirar este item do seu carrinho?",
                object : DialogMessages.Answer {
                    override fun setOnClickListener() {

                        CartControl(context, wsResult, useful).removeItem(cart.idItem!!)
                    }
                })

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}