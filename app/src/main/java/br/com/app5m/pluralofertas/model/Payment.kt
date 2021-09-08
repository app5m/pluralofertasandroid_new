package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Payment: Serializable {
    constructor()

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("date")
    var date: String? = null

    @field:SerializedName("status")
    var status: String? = null

    //credito ou boleto
    @field:SerializedName("tipe")
    var tipe: String? = null
}