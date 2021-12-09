package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cart: Serializable {


    @field:SerializedName("cod")
    var cod: String? = null

    @field:SerializedName("valor_desc")
    var descValue: String? = null

    @field:SerializedName("data_validade")
    var validityDate: String? = null

    @field:SerializedName("derivados")
    var derivativeList: List<Derivative>? = null

    @field:SerializedName("nome_oferta")
    var saleName: String? = null

    @field:SerializedName("id_item")
    var idItem: String? = null

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("id_user")
    var idUser: String? = null

    @field:SerializedName("id_oferta")
    var idSale: String? = null

    @field:SerializedName("valor_uni")
    var unityValue: String? = null

    @field:SerializedName("taxa_servico")
    var servicePrice: String? = null

    @field:SerializedName("id_derivado")
    var idDerivative: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

    @field:SerializedName("msg")
    var msg: String? = null


}