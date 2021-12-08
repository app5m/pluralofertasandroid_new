package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Request: Serializable {


    @field:SerializedName("token")
    var token: String? = null


}