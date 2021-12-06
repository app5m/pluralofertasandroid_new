package br.com.app5m.pluralofertas.controller.webservice

import br.com.app5m.pluralofertas.model.*

interface WSResult {

    //user

//    fun responseU(user: User?, type: String?){}
    fun uResponse(list: List<User>, type: String){}

    fun uAResponse(list: List<UAddress>, type: String){}

    fun sResponse(list: List<Sale>, type: String){}

    fun cResponse(list: List<Cart>, type: String){}

    fun rResponse(list: List<Request>, type: String){}

    fun fResponse(list: List<Freight>, type: String){}

    fun cpResponse(list: List<Coupon>, type: String){}

    fun error(error: String){}

}