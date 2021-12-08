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
import br.com.app5m.pluralofertas.model.*
import br.com.app5m.pluralofertas.util.Useful
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponControl(private val context: Context, private val result: WSResult, private val useful: Useful): Callback<List<Coupon>> {

    private val service = RetrofitInitializer().retrofit(
        true).create(WebService::class.java)
    private val preferences = Preferences(context)
    private var type = ""
    private lateinit var coupon: Coupon

    override fun onResponse(call: Call<List<Coupon>>, response: Response<List<Coupon>>) {
        if (response.isSuccessful){
            response.body()?.let { result.cpResponse(it, type) }
        }else{
            useful.closeLoading()
            SingleToast.INSTANCE.show(context, "Ocorreu um erro não esperado, tente novamente mais tarde.",
                Toast.LENGTH_LONG)
            Log.d("error", "onFailure: " + response.message())
        }
    }

    override fun onFailure(call: Call<List<Coupon>>, t: Throwable) {
        useful.closeLoading()
        SingleToast.INSTANCE.show(context, "Não foi possível contatar o servidor.",
            Toast.LENGTH_LONG)
        Log.d("error", "onFailure: " + t.message)
    }

    fun listCoupons(){

        type = "listCoupons"

        coupon = Coupon()

        coupon.token = WSConstants.TOKEN

        val param: Call<List<Coupon>> = service.listCoupons(preferences.getUserData()!!.id!!, coupon)
        param.enqueue(this)
    }

}