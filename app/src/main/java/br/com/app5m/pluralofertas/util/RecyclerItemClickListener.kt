package br.com.app5m.pluralofertas.util

import br.com.app5m.pluralofertas.model.*


interface RecyclerItemClickListener {


    fun onClickListenerProducts(product: Product){
        //optional body
    }
    fun onClickListenerCupons(coupon: Coupon){
        //optional body
    }
    fun onClickListenerCart(cart: Cart){
        //optional body
    }

    fun onClickListenerUAddress(uaddress: UAddress){
        //optional body
    }

    fun onClickListenerHighlights(highlight: Highlight){
        //optional body
    }
    fun onClickListenerCategoriesSearch(category: Category){
        //optional body
    }
    fun onClickListenerShoopings(shopping: Shopping){
        //optional body
    }
    fun onClickListenerPayments(payment: Payment){
        //optional body
    }
}