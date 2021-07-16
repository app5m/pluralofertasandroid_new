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
import com.example.pluralofertasandroid2.model.Cart

class CartAdapter (private val context: Context, private val listCart: List<Cart>,
                   private val clickOnListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_cart,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return CartViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = listCart[position]

        holder.productNameCartTv.text = "Nome do produto"
        holder.valueProductTv.text = "100,00"

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerCart(cart) }

    }

    override fun getItemCount(): Int {
        return listCart.size
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameCartTv: TextView
        val valueProductTv: TextView
        val productImageIv: ImageView

        init {
            productNameCartTv = itemView.findViewById(R.id.productNameCartTv)
            valueProductTv = itemView.findViewById(R.id.valueProductTv)
            productImageIv = itemView.findViewById(R.id.productImageIv)


        }
    }
}