package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Details: Serializable {

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("nome")
    var name: String? = null

    @field:SerializedName("capa")
    var placeHolder: String? = null

    @field:SerializedName("tipo")
    var type: String? = null

    @field:SerializedName("valor")
    var value: String? = null

    @field:SerializedName("valor_final_desconto")
    var valueFinalDiscount: String? = null

    @field:SerializedName("taxa_servico")
    var servicePrice: String? = null

    @field:SerializedName("desconto")
    var discount: String? = null

}