package br.com.app5m.pluralofertas.controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.controller.webservice.WebService
import br.com.app5m.pluralofertas.config.RetrofitInitializer
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.util.Useful
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaleControl(private val context: Context, private val result: WSResult, private val useful: Useful): Callback<List<Sale>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private lateinit var sale: Sale

    override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
        if (response.isSuccessful){
            response.body()?.let { result.sResponse(it, type) }
        }else{
            useful.closeLoading()
            SingleToast.INSTANCE.show(context, "Ocorreu um erro não esperado, tente novamente mais tarde.",
                Toast.LENGTH_LONG)
            Log.d("error", "onFailure: " + response.message())
        }
    }

    override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
        useful.closeLoading()
        SingleToast.INSTANCE.show(context, "Não foi possível contatar o servidor.",
            Toast.LENGTH_LONG)
        Log.d("error", "onFailure: " + t.message)
    }

    fun findSale(sale: Sale){

        type = "findSale"
/*
        {
            "token": "plural_ofertas@2021",
            "id_endereco_user": 2
        }
        */
        sale.token = WSConstants().TOKEN

        val param: Call<List<Sale>> = service.findSale(sale)
        param.enqueue(this)
    }

    fun listIdSales(sale: Sale){

        type = "listIdSale"
/*
        {
            "token": "plural_ofertas@2021",
        }
        */
        sale.token = WSConstants().TOKEN

        val param: Call<List<Sale>> = service.listIdSale("",sale)
        param.enqueue(this)
    }


    fun listCoupons(sale: Sale){

        type = "listCoupons"
/*
        {
            "token": "plural_ofertas@2021",
        }
        */
        sale.token = WSConstants().TOKEN

        val param: Call<List<Sale>> = service.listCoupons("",sale)
        param.enqueue(this)
    }

}