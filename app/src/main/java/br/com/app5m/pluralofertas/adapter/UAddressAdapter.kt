package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.UAddress

class UAddressAdapter (private val context: Context, private val listUAddress: List<UAddress>,
                       private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<UAddressAdapter.UAddressViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UAddressViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_uaddress,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return UAddressViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: UAddressViewHolder, position: Int) {
        val uaddress = listUAddress[position]
/*

        holder.productNameCartTv.text = "Nome do produto"
        holder.valueProductTv.text = "100,00"
*/

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerUAddress(uaddress) }

    }

    override fun getItemCount(): Int {
        return listUAddress.size
    }

    class UAddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       /* val productNameCartTv: TextView
        val valueProductTv: TextView
        val productImageIv: ImageView

        init {
            productNameCartTv = itemView.findViewById(R.id.productNameCartTv)
            valueProductTv = itemView.findViewById(R.id.valueProductTv)
            productImageIv = itemView.findViewById(R.id.productImageIv)


        }*/
    }
}