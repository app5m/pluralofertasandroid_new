package com.example.pluralofertasandroid2.helper

import com.example.pluralofertasandroid2.model.*


interface RecyclerItemClickListener {


    fun onClickListenerNews(product: Product){
        //optional body
    }
    fun onClickListenerCupons(cupon: Cupon){
        //optional body
    }
    fun onClickListenerCart(cart: Cart){
        //optional body
    }fun onClickListenerUAddress(uaddress: UAddress){
        //optional body
    }


    fun onClickListenerHighlights(highlight: Highlight){
        //optional body
    }
}