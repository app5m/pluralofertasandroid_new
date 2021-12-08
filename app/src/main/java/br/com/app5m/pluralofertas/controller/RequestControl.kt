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

        type = "add"
/*
{
   "id_user":"4",
   "id_carrinho":6,
   "id_endereco":2,
   "forma_pagamento":2,
   "id_frete":1,
   "valor_frete": "R$ 20,00",
   "valor_subtotal": "R$ 55,00",
   "valor_desc_cupom": "R$ 5,00",
   "id_cupom": 6,
   "obs":"nenhuma",
   "card_bairro":"Morro Santana",
   "card_celular":"(99) 99999-9999",
   "card_cep":"91260-010",
   "card_cidade":"Porto Alegre",
   "card_complemento":"teste",
   "card_cpf":"29578963033",
   "card_endereco":"Rua Amadeu F. de Oliveira Freitas",
   "card_estado":"RS",
   "card_name":"Android Developer",
   "card_nascimento":"17/11/1996",
   "card_numero":"80",
   "cpf":"29578963033",
   "hash_card":"m95QM6OhymZ/mgPpBkSfP3LlxvyX6ydZGjgGfOxynlSejxqx8/hvLmtvHaUJXQUM1qa+G18i+Ghxd9TyWFV5f9gmttb9Zp9rZTLUk3C81XsInyFjK+Wfeys+u6nKFT8fs9Rk8qF/        GDs74khcfstdcwFrEZvKIMne7AAP1sMnOgxT4puZflONpAwecrKI+9uwhIBPQC+eBj5yNg+l6Jz7m2+tj9GbMoMTecYERwL27XXCJIKE7ewfyv/gJLFOeBcJDLa7f8BOwfaJQJa/d6SokdVPy+1i25Y8m8+hI0St+ gG9RY4nD1NNHyoHz7b5JMRpHdb3/7GB1fUsWTT796gV9A\u003d\u003d",
   "parcelas":"2",
   "plataforma": 1
}
        */

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