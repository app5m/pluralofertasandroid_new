package com.example.pluralofertasandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Cupon

class OpenedCuponsAdapter(private val context: Context, private val listOpenedCupon: List<Cupon>,
                          private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<OpenedCuponsAdapter.OpenedViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenedViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_opened_mycupons, parent, false) // vai conectar com os ids abaixo
        return OpenedViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: OpenedViewHolder, position: Int) {
        val cupon = listOpenedCupon[position]

      /*  holder.productNameTv.text = "Mega Burguer"
        holder.productDescriptionTv.text = "O Mega Burguer vem com 2 carnes e muita salada, o resto é tempeiro. "
        holder.asOfTv.text = "A partir de "
        holder.productValueTv.text = "10,00 "*/

/*
        holder.itemView.setOnClickListener { clickOnListener.onClickListenerNews(cupon)}
*/

    }

    override fun getItemCount(): Int {
        return listOpenedCupon.size
    }

    class OpenedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
