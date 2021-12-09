package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName

class CServico {

    @field:SerializedName("Codigo")
    var cod: String? = null

    @field:SerializedName("Valor")
    var value: String? = null

    @field:SerializedName("PrazoEntrega")
    var deliveryTime: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("msg")
    var msg: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

}