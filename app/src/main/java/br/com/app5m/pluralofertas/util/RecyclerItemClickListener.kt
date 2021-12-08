package br.com.app5m.pluralofertas.util

import br.com.app5m.pluralofertas.model.*


interface RecyclerItemClickListener {


    fun onClickListenerSale(sale: Sale){
        //optional body
    }
    fun onClickListenerCoupon(coupon: Coupon){
        //optional body
    }
    fun onClickListenerCart(cart: Cart){
        //optional body
    }

    fun onClickListenerUAddress(uaddress: UAddress){
        //optional body
    }

    fun onClickListenerHighlight(highlight: Highlight){
        //optional body
    }
    fun onClickListenerCategoriesSearch(category: Category){
        //optional body
    }
    fun onClickListenerRequest(request: Request){
        //optional body
    }
    fun onClickListenerPayments(payment: Payment){
        //optional body
    }
}