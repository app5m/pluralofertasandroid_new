package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Freight: Serializable {


    @field:SerializedName("cep_destino")
    var destinyCep: String? = null

    @field:SerializedName("cod")
    var cod: String? = null

    @field:SerializedName("msg")
    var msg: String? = null

    @field:SerializedName("id_carrinho")
    var cartId: String? = null

    @field:SerializedName("cServico")
    var cService: CServico? = null

}