package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Photo : Serializable{

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("url")
    var url: String? = null

    @field:SerializedName("foto")
    var photo: String? = null

    @field:SerializedName("capa")
    var capa: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

}