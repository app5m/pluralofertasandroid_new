package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener

class RequestAdapter(private val context: Context, private val list: List<Request>,
                     private val recyclerItemClickListener: RecyclerItemClickListener
) : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val requestInfoTv: TextView = itemView.findViewById(R.id.requestInfoTv)
        val valueTv: TextView = itemView.findViewById(R.id.value_tv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_requests, parent, false) // vai conectar com os ids abaixo
        return ViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = list[position]

        holder.requestInfoTv.text = request.sale +
                "\n\n" + "Tipo: " + request.typeDelivery +
                "\n" + "Método de pagamento: " + request.typePayment +
                "\n" + "Data: " + request.date +
                "\n\n" + "Status do pagamento: " + request.statusPayment

        holder.valueTv.text = request.totalValue


        holder.itemView.setOnClickListener { recyclerItemClickListener.onClickListenerRequest(request)}

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
