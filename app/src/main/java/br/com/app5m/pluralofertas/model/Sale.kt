package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Sale: Serializable {

    @field:SerializedName("nome")
    var name: String? = null

    @field:SerializedName("valor")
    var value: String? = null

    @field:SerializedName("capa")
    var placeHolder: String? = null

    @field:SerializedName("detalhes")
    var details: Details? = null

    @field:SerializedName("derivados")
    var derivativeList: List<Derivative>? = null

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

    @field:SerializedName("fotos")
    var photoList: List<Photo>? = null
}