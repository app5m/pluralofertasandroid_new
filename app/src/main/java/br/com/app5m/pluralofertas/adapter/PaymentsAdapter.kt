package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Payment

class PaymentsAdapter(private val context: Context, private val listPayment: List<Payment>,
                      private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_payments, parent, false) // vai conectar com os ids abaixo
        return PaymentViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = listPayment[position]

      /*  holder.productNameTv.text = "Mega Burguer"
        holder.productDescriptionTv.text = "O Mega Burguer vem com 2 carnes e muita salada, o resto é tempeiro. "
        holder.asOfTv.text = "A partir de "
        holder.productValueTv.text = "10,00 "*/

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerPayments(payment)}

    }

    override fun getItemCount(): Int {
        return listPayment.size
    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val productNameTv: TextView
        val productDescriptionTv: TextView
        val asOfTv: TextView
        val productValueTv: TextView
        val productImageIv: ImageView

        init {
            productNameTv = itemView.findViewById(R.id.productNameTv)
            productDescriptionTv = itemView.findViewById(R.id.productDescriptionTv)
            asOfTv = itemView.findViewById(R.id.asOfTv)
            productValueTv = itemView.findViewById(R.id.productValueTv)
            productImageIv = itemView.findViewById(R.id.imageProductsIv)

        }*/
    }

}
