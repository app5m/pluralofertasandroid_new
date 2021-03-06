package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Highlight: Serializable {

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("img")
    var url: String? = null

    @field:SerializedName("rows")
    var rows: String? = null


}