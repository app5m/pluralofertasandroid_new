package br.com.app5m.pluralofertas.controller.webservice

import br.com.app5m.pluralofertas.model.User

interface WSResult {

    //user

//    fun responseU(user: User?, type: String?){}
    fun uResponse(list: List<User>, type: String){}

    fun error(error: String){}

}