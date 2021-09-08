package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Highlight


class HighlightsAdapter (val context: Context, private val highlightList: List<Highlight>, private val clickOnListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<HighlightsAdapter.HighlightsHolder>(){

   /* class HighlightsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageHighLightsIv)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsHolder {
        return HighlightsHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_highlights, parent, false))
    }

    override fun onBindViewHolder(holder: HighlightsHolder, position: Int) {
        val highlight = highlightList[position]

        holder.namehighLightTv.text = "Nome do produto em duas linhas e também em três linhas"
        holder.highligtiValueTv.text = "10,00 "
        holder.highLightasOfTv.text = "A partir de "
        holder.highLightOffTv.text = "50% OFF"

        holder.itemView.setOnClickListener { clickOnListener.onClickListenerHighlights(highlight)}

    }

    override fun getItemCount(): Int {
        return highlightList.size
    }

    class HighlightsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namehighLightTv: TextView
        val highligtiValueTv: TextView
        val highLightasOfTv: TextView
        val highLightOffTv: TextView
        val highLightsMainIv: ImageView

        init {
            namehighLightTv = itemView.findViewById(R.id.namehighLightTv)
            highLightasOfTv = itemView.findViewById(R.id.highLightasOfTv)
            highligtiValueTv = itemView.findViewById(R.id.highligtiValueTv)
            highLightOffTv = itemView.findViewById(R.id.highLightOffTv)
            highLightsMainIv = itemView.findViewById(R.id.highLightsMainIv)


        }
    }

}