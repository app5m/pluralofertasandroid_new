package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Derivative: Serializable {

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

    @field:SerializedName("nome")
    var name: String? = null

    @field:SerializedName("descricao")
    var desc: String? = null

    @field:SerializedName("valor")
    var value: String? = null
}