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

class ProductsAdapter(private val context: Context, private val listProduct: List<Product>,
                      private val clickOnListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_products, parent, false) // vai conectar com os ids abaixo
        return ProductsViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = listProduct[position]

        holder.productNameTv.text = "Mega Burguer"
        holder.productDescriptionTv.text = "O Mega Burguer vem com 2 carnes e muita salada, o resto é tempeiro. "
        holder.asOfTv.text = "A partir de "
        holder.productValueTv.text = "10,00 "

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerNews(product)}

    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTv: TextView
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

        }
    }

}
