package com.example.pluralofertasandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Highlights

class HighlightsAdapter (val context: Context, val highlightsList: List<Highlights>, clickListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<HighlightsAdapter.HighlightsHolder>(){

    class HighlightsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageHighLightsIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsHolder {
        return HighlightsHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_highlights, parent, false))
    }

    override fun onBindViewHolder(holder: HighlightsHolder, position: Int) {


        holder.image.setOnClickListener {

/*
            MyMessages(context).insertZoom(WSConstants().imagesSchedule + photoList[position].url)
*/
        }

    }

    override fun getItemCount(): Int {
        return highlightsList.size
    }

}