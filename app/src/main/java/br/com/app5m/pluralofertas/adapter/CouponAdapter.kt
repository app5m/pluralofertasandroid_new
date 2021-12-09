package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Coupon


class CouponAdapter (private val context: Context, private val list: List<Coupon>,
                     private val recyclerItemClickListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    private var selectedPosition = -1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val couponLL: LinearLayout = itemView.findViewById(R.id.coupon_ll)
        val validityTv: TextView = itemView.findViewById(R.id.validity_tv)
        val descValueTv: TextView = itemView.findViewById(R.id.descValue_tv)
        val couponPositionTv: TextView = itemView.findViewById(R.id.positionCouponTv)
        val couponCb: CheckBox = itemView.findViewById(R.id.coupon_cb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_coupons,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return ViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coupon = list[position]

        holder.couponPositionTv.text = "Cupom " + (position + 1)

        holder.validityTv.text = coupon.validityDate
        holder.descValueTv.text = coupon.descValue

        if (selectedPosition == position) {
            recyclerItemClickListener.onClickListenerCoupon(coupon)

            holder.couponCb.isChecked = true
            holder.couponLL.background = context.resources.getDrawable(R.drawable.shape_gray_stroke_gray_button)

        } else {
            recyclerItemClickListener?.onClickListenerDerivative(null)

            holder.couponCb.isChecked = false
            holder.couponLL.background = context.resources.getDrawable(R.drawable.shape_gray_button)
        }

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)

            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}