package br.com.app5m.pluralofertas.adapter

import android.annotation.SuppressLint
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
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.model.Derivative

class DerivativesAdapter (private val context: Context, private val list: List<Derivative>,
                          private val recyclerItemClickListener: RecyclerItemClickListener?) : RecyclerView.Adapter<DerivativesAdapter.ViewHolder>() {

    private var selectedPosition = -1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val derivativeLL: LinearLayout = itemView.findViewById(R.id.derivative_ll)
        val nameTv: TextView = itemView.findViewById(R.id.derivativeName_tv)
        val valueTv: TextView = itemView.findViewById(R.id.derivativeValue_tv)
        val descTv: TextView = itemView.findViewById(R.id.desc_tv)
        val derivativeCb: CheckBox = itemView.findViewById(R.id.derivative_cb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_derivatives, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val derivative = list[position]

        holder.nameTv.text = derivative.name
        holder.valueTv.text = derivative.value
        holder.descTv.text = derivative.desc

        if (selectedPosition == position) {
            recyclerItemClickListener?.onClickListenerDerivative(derivative)

            holder.derivativeCb.isChecked = true
            holder.derivativeLL.background = context.resources.getDrawable(R.drawable.shape_gray_stroke_gray_button)

        } else {
            recyclerItemClickListener?.onClickListenerDerivative(null)

            holder.derivativeCb.isChecked = false
            holder.derivativeLL.background = context.resources.getDrawable(R.drawable.shape_gray_button)
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