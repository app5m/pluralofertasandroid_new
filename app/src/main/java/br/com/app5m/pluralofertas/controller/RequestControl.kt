package br.com.app5m.pluralofertas.controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.controller.webservice.WebService
import br.com.app5m.pluralofertas.config.RetrofitInitializer
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.util.Useful
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestControl(private val context: Context, private val result: WSResult, private val useful: Useful): Callback<List<Request>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private lateinit var request: Request

    override fun onResponse(call: Call<List<Request>>, response: Response<List<Request>>) {
        if (response.isSuccessful){
            response.body()?.let { result.rResponse(it, type) }
        }else{
            useful.closeLoading()
            SingleToast.INSTANCE.show(context, "Ocorreu um erro não esperado, tente novamente mais tarde.",
                Toast.LENGTH_LONG)
            Log.d("error", "onFailure: " + response.message())
        }
    }

    override fun onFailure(call: Call<List<Request>>, t: Throwable) {
        useful.closeLoading()
        SingleToast.INSTANCE.show(context, "Não foi possível contatar o servidor.",
            Toast.LENGTH_LONG)
        Log.d("error", "onFailure: " + t.message)
    }


    fun newRequest(request: Request){

        type = "new"

        request.idUser = preferences.getUserData()!!.id

        val param: Call<List<Request>> = service.newRequest(request)
        param.enqueue(this)
    }

    fun find(){

        type = "find"

        request = Request()

        request.token = WSConstants.TOKEN

        val param: Call<List<Request>> = service.find(preferences.getUserData()!!.id!!, request)
        param.enqueue(this)
    }

    fun findId(request: Request){

        type = "findId"
/*
{
    "token": "plural_ofertas@2021"
}
        */

        val param: Call<List<Request>> = service.findId("", request)
        param.enqueue(this)
    }

}