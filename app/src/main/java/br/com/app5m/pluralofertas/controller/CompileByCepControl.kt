package br.com.app5m.pluralofertas.controller

import android.content.Context
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WebService
import br.com.app5m.pluralofertas.model.UAddress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 */
object CompileByCepControl {

    fun compile(cep: String?, resultCep: CepResult) {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(WSConstants.VIACEP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val webService: WebService = retrofit.create(WebService::class.java)
        val callCEPByCEP: Call<UAddress> = webService.getAddressByCEP(cep)

        callCEPByCEP.enqueue(object : Callback<UAddress> {

            override fun onResponse(call: Call<UAddress>, response: Response<UAddress>) {
                if (response.isSuccessful) response.body()?.let { resultCep.resultCep(it) }
            }

            override fun onFailure(call: Call<UAddress>, t: Throwable) {}
        })
    }

    interface CepResult {
        fun resultCep(cep: UAddress)
    }
}