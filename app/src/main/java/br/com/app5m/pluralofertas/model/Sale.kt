package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Sale: Serializable {

    @field:SerializedName("detalhes")
    var datails: Details? = null

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("id_endereco_user")
    var addressIdUser: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("latitude")
    var latitude: String? = null

    @field:SerializedName("longitude")
    var longitude: String? = null
}