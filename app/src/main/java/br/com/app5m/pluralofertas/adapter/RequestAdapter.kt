package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener

class RequestAdapter(private val context: Context, private val list: List<Request>,
                     private val clickOnListener: RecyclerItemClickListener
) : RecyclerView.Adapter<RequestAdapter.ShoopingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoopingViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_shoopings, parent, false) // vai conectar com os ids abaixo
        return ShoopingViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ShoopingViewHolder, position: Int) {
        val shooping = list[position]

      /*  holder.productNameTv.text = "Mega Burguer"
        holder.productDescriptionTv.text = "O Mega Burguer vem com 2 carnes e muita salada, o resto é tempeiro. "
        holder.asOfTv.text = "A partir de "
        holder.productValueTv.text = "10,00 "*/

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerRequest(shooping)}

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ShoopingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
