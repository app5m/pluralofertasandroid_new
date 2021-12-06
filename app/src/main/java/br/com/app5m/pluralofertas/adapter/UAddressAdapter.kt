package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.model.UAddress

class UAddressAdapter (private val context: Context, private val listUAddress: List<UAddress>,
                       private val alertDialog: androidx.appcompat.app.AlertDialog)
    : RecyclerView.Adapter<UAddressAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val removeIb: ImageButton = v.findViewById(R.id.remove_imageButton)
        private val cityStateTv: TextView = v.findViewById(R.id.cityState_textView)
        private val nbhTv: TextView = v.findViewById(R.id.nbh_textView)
        private val addresstv: TextView = v.findViewById(R.id.address_textView)
        private val complementTv: TextView = v.findViewById(R.id.complement_textView)
        private val addressRb: RadioButton = v.findViewById(R.id.address_radioButton)
        private val currentLocationTv: TextView = v.findViewById(R.id.currentLocation_textView)
        private val numTv: TextView = v.findViewById(R.id.number_textView)

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val uaddress = listUAddress[position + 1]

        if (position == 0) {

            //primeira
        } else {

        }

    }

    override fun getItemCount(): Int {
        return listUAddress.size
    }

}