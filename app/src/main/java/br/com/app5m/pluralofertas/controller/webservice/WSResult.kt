package br.com.app5m.pluralofertas.controller.webservice

import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User

interface WSResult {

    //user

//    fun responseU(user: User?, type: String?){}
    fun uResponse(list: List<User>, type: String){}

    fun uAResponse(list: List<UAddress>, type: String){}

    fun sResponse(list: List<Sale>, type: String){}

    fun error(error: String){}

}