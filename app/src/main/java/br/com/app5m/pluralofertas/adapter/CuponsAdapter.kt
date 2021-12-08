package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Coupon


class CuponsAdapter (private val context: Context, private val listCoupons: List<Coupon>,
                     private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<CuponsAdapter.CuponsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuponsViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_cupons,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return CuponsViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: CuponsViewHolder, position: Int) {
        val cupon = listCoupons[position]

        holder.openCuponNameTv.text = "Nome do Cupom"
        holder.descriptionOpenCuponTv.text = "Suspendisse sodales, metus sed aliquet ultricies, arcu diam cursus odio, auctor ultrices libero lacus nec eros. Donec laoreet at dui ut feugiat."
        holder.priceOpenCuponTv.text = "100,00"
        holder.cuponCodeValueTv.text = "TPR2FR"
        holder.expirationDateCuponTv.text = "Válido até 31/03/2019."
        holder.itemView.setOnClickListener { clickOnListener.onClickListenerCoupon(cupon) }
        /*holder.itemView.setOnClickListener { clickOnListener.onClickListenerNews(cupon) }*/

    }

    override fun getItemCount(): Int {
        return listCoupons.size
    }

    class CuponsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val openCuponNameTv: TextView
        val descriptionOpenCuponTv: TextView
        val cuponCodeValueTv: TextView
        val priceOpenCuponTv: TextView
        val expirationDateCuponTv: TextView
        val imageCupontIv: ImageView

        init {
            openCuponNameTv = itemView.findViewById(R.id.openCuponNameTv)
            descriptionOpenCuponTv = itemView.findViewById(R.id.descriptionOpenCuponTv)
            cuponCodeValueTv = itemView.findViewById(R.id.cuponCodeValueTv)
            priceOpenCuponTv = itemView.findViewById(R.id.priceOpenCuponTv)
            expirationDateCuponTv = itemView.findViewById(R.id.expirationDateCuponTv)
            imageCupontIv = itemView.findViewById(R.id.imageHighLightsIv)


        }
    }
}