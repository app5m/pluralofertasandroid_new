package br.com.app5m.pluralofertas.controller

import android.content.Context
import android.util.Log
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.controller.webservice.WebService
import br.com.app5m.pluralofertas.config.RetrofitInitializer
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaleControl(context: Context, private val result: WSResult): Callback<List<Sale>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private val sale = Sale()

    override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
        if (response.isSuccessful){
            response.body()?.let { result.sResponse(it, type) }
        }else{
            result.error("Erro não esperado.")
        }
    }

    override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
        result.error("Não foi possível contatar o servidor.")
        Log.d("error", "onFailure: " + t.message)
    }

    fun findSale(sale: Sale){

        type = "findSale"

        sale.token = WSConstants().TOKEN

        val param: Call<List<Sale>> = service.findSale("", "", "", "", sale)
        param.enqueue(this)
    }

}