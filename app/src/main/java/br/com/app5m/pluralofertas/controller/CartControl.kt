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
import br.com.app5m.pluralofertas.model.Cart
import br.com.app5m.pluralofertas.util.Useful
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartControl(private val context: Context, private val result: WSResult, private val useful: Useful): Callback<List<Cart>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private lateinit var cart: Cart

    override fun onResponse(call: Call<List<Cart>>, response: Response<List<Cart>>) {
        if (response.isSuccessful){
            response.body()?.let { result.cResponse(it, type) }
        }else{
            useful.closeLoading()
            SingleToast.INSTANCE.show(context, "Ocorreu um erro não esperado, tente novamente mais tarde.",
                Toast.LENGTH_LONG)
            Log.d("error", "onFailure: " + response.message())
        }
    }

    override fun onFailure(call: Call<List<Cart>>, t: Throwable) {
        useful.closeLoading()
        SingleToast.INSTANCE.show(context, "Não foi possível contatar o servidor.",
            Toast.LENGTH_LONG)
        Log.d("error", "onFailure: " + t.message)
    }


    fun addItem(cart: Cart){

        type = "add"

        cart.idUser = preferences.getUserData()!!.id
        cart.token = WSConstants.TOKEN

        val param: Call<List<Cart>> = service.addItem(cart)
        param.enqueue(this)
    }

    fun addCoupon(cart: Cart){

        type = "addCoupon"

        cart.token = WSConstants.TOKEN

        val param: Call<List<Cart>> = service.addCoupon(cart)
        param.enqueue(this)
    }

    fun listItems(idCart: String){

        type = "listItems"

        cart = Cart()

        cart.token = WSConstants.TOKEN

        val param: Call<List<Cart>> = service.listItems(idCart, cart)
        param.enqueue(this)
    }

    fun removeItem(idItem: String){

        type = "remove"

        cart = Cart()

        cart.token = WSConstants.TOKEN

        val param: Call<List<Cart>> = service.removeItem(idItem, cart)
        param.enqueue(this)
    }

}