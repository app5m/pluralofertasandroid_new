package com.example.pluralofertasandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Photo

class PhotoAdapter (val context: Context, val photoList: List<Photo>, clickListener: RecyclerItemClickListener)
    : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>(){

    class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageCupontIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_destaque, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {


        holder.image.setOnClickListener {

/*
            MyMessages(context).insertZoom(WSConstants().imagesSchedule + photoList[position].url)
*/
        }

    }

    override fun getItemCount(): Int {
        return photoList.size
    }

}