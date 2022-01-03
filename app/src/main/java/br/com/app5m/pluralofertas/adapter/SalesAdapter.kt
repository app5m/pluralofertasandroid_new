package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Sale
import com.bumptech.glide.Glide

class SalesAdapter(private val context: Context, private val list: List<Sale>,
                   private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<SalesAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val saleNameTv: TextView = itemView.findViewById(R.id.nameTv)
        val saleValueTv: TextView = itemView.findViewById(R.id.valueTv)
        val saleIv: ImageView = itemView.findViewById(R.id.imageHolderSaleIv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_sales, parent, false)
        return ProductsViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val sale = list[position]

        holder.saleNameTv.text = sale.name
        holder.saleValueTv.text = sale.value

        if (sale.placeHolder != null) {
            Glide.with(context).load(WSConstants.SALE_URL + sale.placeHolder).into(holder.saleIv)
        }

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerSale(sale)}

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
