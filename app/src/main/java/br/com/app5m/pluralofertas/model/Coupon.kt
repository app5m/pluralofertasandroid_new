package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Coupon: Serializable {

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("cod")
    var cod: String? = null

    @field:SerializedName("valor_desc")
    var descValue: String? = null

    @field:SerializedName("data_validade")
    var validityDate: String? = null

    @field:SerializedName("rows")
    var rows: String? = null
}