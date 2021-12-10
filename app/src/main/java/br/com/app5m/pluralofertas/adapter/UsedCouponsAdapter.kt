package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Coupon

class UsedCouponsAdapter(private val context: Context, private val list: List<Coupon>,
                         private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<UsedCouponsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val validityTv: TextView = itemView.findViewById(R.id.validity_tv)
        val descValueTv: TextView = itemView.findViewById(R.id.descValue_tv)
        val couponPositionTv: TextView = itemView.findViewById(R.id.positionCouponTv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_used_mycupons, parent, false) // vai conectar com os ids abaixo
        return ViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val coupon = list[position]

        holder.couponPositionTv.text = "Código do cupom: " + coupon.cod

        holder.validityTv.text = "Validade: " + coupon.validityDate
        holder.descValueTv.text = "Desconto: " + coupon.descValue


    }

    override fun getItemCount(): Int {
        return list.size
    }


}
