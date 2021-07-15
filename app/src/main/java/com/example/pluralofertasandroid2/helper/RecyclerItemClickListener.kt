package com.example.pluralofertasandroid2.helper

import com.example.pluralofertasandroid2.model.Cupon
import com.example.pluralofertasandroid2.model.Product


interface RecyclerItemClickListener {


    fun onClickListenerNews(product: Product){
        //optional body
    }
    fun onClickListenerCupons(cupon: Cupon){
        //optional body
    }
}