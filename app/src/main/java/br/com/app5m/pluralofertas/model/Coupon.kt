package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Coupon: Serializable {

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("token")
    var token: String? = null


}