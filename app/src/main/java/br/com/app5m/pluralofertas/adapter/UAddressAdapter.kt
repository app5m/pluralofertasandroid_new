package br.com.app5m.pluralofertas.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful

class UAddressAdapter (private val context: Context, private val listUAddress: List<UAddress>,
                       private val useful: Useful, private val recyclerItemClickListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<UAddressAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val removeIb: ImageButton = v.findViewById(R.id.remove_imageButton)
        val cityStateTv: TextView = v.findViewById(R.id.cityState_textView)
        val nbhTv: TextView = v.findViewById(R.id.nbh_textView)
        val addresstv: TextView = v.findViewById(R.id.address_textView)
        val complementTv: TextView = v.findViewById(R.id.complement_textView)
        val addressRb: RadioButton = v.findViewById(R.id.address_radioButton)
        val positionTv: TextView = v.findViewById(R.id.addressPos_textView)
        val numTv: TextView = v.findViewById(R.id.number_textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_uaddress,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return ViewHolder(listItem)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val uaddress = listUAddress[position]

        holder.positionTv.text = "Endereço " + (position + 1)

        UAddressControl(context, object: WSResult {
            override fun uAResponse(list: List<UAddress>, type: String) {

                val responseInfo = list[0]

                holder.cityStateTv.text = responseInfo.city + " (" + responseInfo.state + ")"
                holder.nbhTv.text = responseInfo.neighborhood
                holder.addresstv.text = responseInfo.address
                holder.numTv.text = responseInfo.number
                holder.complementTv.text = responseInfo.complement

            }

        }, useful).listIdAddress(uaddress.id!!)

        holder.itemView.setOnClickListener {

            recyclerItemClickListener.onClickListenerUAddress(uaddress)
        }

    }

    override fun getItemCount(): Int {
        return listUAddress.size
    }

}