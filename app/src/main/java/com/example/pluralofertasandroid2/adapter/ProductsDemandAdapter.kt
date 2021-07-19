package com.example.pluralofertasandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Product

class ProductsDemandAdapter(private val context: Context, private val listProductDemand: List<Product>,
                            private val clickOnListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<ProductsDemandAdapter.ProductsDemandViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsDemandViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_productsdemand, parent, false) // vai conectar com os ids abaixo
        return ProductsDemandViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ProductsDemandViewHolder, position: Int) {
        val product = listProductDemand[position]

        holder.demandNameTv.text = "Descrição do Pedido"
        holder.dateDemandTv.text = "Data  06/07/2021"
        holder.timeDemandTv.text = "Às 14:30"
        holder.demandValueTv.text = "00,00 "
        holder.demandRsTv.text = "R$"
        holder.statusDemandTv.text = "aprovado"

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerProducts(product)}

    }

    override fun getItemCount(): Int {
        return listProductDemand.size
    }

    class ProductsDemandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val demandNameTv: TextView
        val dateDemandTv: TextView
        val timeDemandTv: TextView
        val demandValueTv: TextView
        val demandRsTv: TextView
        val statusDemandTv: TextView

        init {
            demandNameTv = itemView.findViewById(R.id.demandNameTv)
            dateDemandTv = itemView.findViewById(R.id.dateDemandTv)
            timeDemandTv = itemView.findViewById(R.id.timeDemandTv)
            demandValueTv = itemView.findViewById(R.id.demandValueTv)
            demandRsTv = itemView.findViewById(R.id.demandRsTv)
            statusDemandTv = itemView.findViewById(R.id.statusDemandTv)

        }
    }

}
