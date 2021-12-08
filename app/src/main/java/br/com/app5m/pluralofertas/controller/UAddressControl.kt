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
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.util.Useful
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UAddressControl(private val context: Context, private val result: WSResult, private val useful: Useful): Callback<List<UAddress>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private lateinit var uAddress : UAddress

    override fun onResponse(call: Call<List<UAddress>>, response: Response<List<UAddress>>) {
        if (response.isSuccessful){
            response.body()?.let { result.uAResponse(it, type) }
        }else{
            useful.closeLoading()
            SingleToast.INSTANCE.show(context, "Ocorreu um erro não esperado, tente novamente mais tarde.",
                Toast.LENGTH_LONG)
            Log.d("error", "onFailure: " + response.message())
        }
    }

    override fun onFailure(call: Call<List<UAddress>>, t: Throwable) {
        useful.closeLoading()
        SingleToast.INSTANCE.show(context, "Não foi possível contatar o servidor.",
            Toast.LENGTH_LONG)
        Log.d("error", "onFailure: " + t.message)
    }

    fun findAddress(){

        type = "findAddress"

        uAddress = UAddress()

        uAddress.token = WSConstants.TOKEN

        val param: Call<List<UAddress>> = service.findAddress(preferences.getUserData()!!.id!!, uAddress)
        param.enqueue(this)
    }

    fun saveAddress(uAddress: UAddress){

        type = "saveAddress"

        uAddress.idUser = preferences.getUserData()!!.id
        uAddress.token = WSConstants.TOKEN

        val param: Call<List<UAddress>> = service.saveAddress(uAddress)
        param.enqueue(this)
    }

    fun listIdAddress(idUser: String){

        type = "listIdAddress"

        uAddress.token = WSConstants.TOKEN

        val param: Call<List<UAddress>> = service.listIdAddress(idUser, uAddress)
        param.enqueue(this)
    }


    fun updateAddress(uAddress: UAddress){

        type = "updateAddress"


        uAddress.token = WSConstants.TOKEN

        val param: Call<List<UAddress>> = service.updateAddressData(uAddress)
        param.enqueue(this)
    }

}