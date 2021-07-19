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

        holder.demandNameTv.text = "Nome do produto em duas linhas e também em três linhas........................................................................................."
        holder.demandDescriptionTv.text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor. "
        holder.demandValueTitleTv.text = "Valor "
        holder.demandValueTv.text = "00,00 "
        holder.demandRsTv.text = "R$"

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerProducts(product)}

    }

    override fun getItemCount(): Int {
        return listProductDemand.size
    }

    class ProductsDemandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val demandNameTv: TextView
        val demandDescriptionTv: TextView
        val demandValueTitleTv: TextView
        val demandValueTv: TextView
        val demandRsTv: TextView

        init {
            demandNameTv = itemView.findViewById(R.id.demandNameTv)
            demandDescriptionTv = itemView.findViewById(R.id.demandDescriptionTv)
            demandValueTitleTv = itemView.findViewById(R.id.demandValueTitleTv)
            demandValueTv = itemView.findViewById(R.id.demandValueTv)
            demandRsTv = itemView.findViewById(R.id.demandRsTv)

        }
    }

}
