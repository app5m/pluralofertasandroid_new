package com.example.pluralofertasandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Shooping

class ShoopingsAdapter(private val context: Context, private val listShooping: List<Shooping>,
                       private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<ShoopingsAdapter.ShoopingViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoopingViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_shoopings, parent, false) // vai conectar com os ids abaixo
        return ShoopingViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ShoopingViewHolder, position: Int) {
        val shooping = listShooping[position]

      /*  holder.productNameTv.text = "Mega Burguer"
        holder.productDescriptionTv.text = "O Mega Burguer vem com 2 carnes e muita salada, o resto é tempeiro. "
        holder.asOfTv.text = "A partir de "
        holder.productValueTv.text = "10,00 "*/

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerShoopings(shooping)}

    }

    override fun getItemCount(): Int {
        return listShooping.size
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
