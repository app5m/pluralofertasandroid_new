package br.com.app5m.pluralofertas.controller

import android.content.Context
import android.util.Log
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.controller.webservice.WebService
import br.com.app5m.pluralofertas.config.RetrofitInitializer
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UAddressControl(context: Context, private val result: WSResult): Callback<List<UAddress>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private val uAddress = UAddress()

    override fun onResponse(call: Call<List<UAddress>>, response: Response<List<UAddress>>) {
        if (response.isSuccessful){
            response.body()?.let { result.uAResponse(it, type) }
        }else{
            result.error("Erro não esperado.")
        }
    }

    override fun onFailure(call: Call<List<UAddress>>, t: Throwable) {
        result.error("Não foi possível contatar o servidor.")
        Log.d("error", "onFailure: " + t.message)
    }

    fun findAddress(password: String){

        type = "findAddress"

//direto na url

//        uAddress.token = WSConstants().TOKEN

        val param: Call<List<UAddress>> = service.findAddress(uAddress)
        param.enqueue(this)
    }

    fun saveAddress(uAddress: UAddress){

        type = "saveAddress"

        uAddress.token = WSConstants().TOKEN

        val param: Call<List<UAddress>> = service.saveAddress(uAddress)
        param.enqueue(this)
    }

    fun listIdAddress(uAddress: UAddress){

        type = "listIdAddress"

        //direto na url

//        uAddress.token = WSConstants().TOKEN

        val param: Call<List<UAddress>> = service.listIdAddress(uAddress)
        param.enqueue(this)
    }


    fun updateAddress(uAddress: UAddress){

        type = "updateAddress"

/*
        {
            "id": 1,
            "token": "plural_ofertas@2021",
            "cidade": "Alvorada",
            "estado": "RS",
            "endereco": "Av. Tiradentes",
            "numero": "81",
            "bairro": "Bela Vista"
        }*/

//        uAddress.token = WSConstants().TOKEN

        val param: Call<List<UAddress>> = service.saveAddress(uAddress)
        param.enqueue(this)
    }

}