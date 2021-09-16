package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UAddress: Serializable {

    constructor()
    constructor(
        titulo: String?,
        rua: String?,
        bairro: String?,
        numero: String?,
        tipoderesidencia: String?,
        logradouro: String?,
        cep: String?,
    ) {
        this.titulo = titulo
        this.rua = rua
        this.bairro = bairro
        this.numero = numero
        this.tipoderesidencia = tipoderesidencia
        this.logradouro = logradouro
        this.cep = cep
    }

    var titulo: String? = null
    var rua: String? = null
    var bairro: String? = null
    var numero: String? = null
    var tipoderesidencia: String? = null
    var logradouro: String? = null


    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("latitude")
    var latitude: String? = null

    @field:SerializedName("longitude")
    var longitude: String? = null

    @field:SerializedName("cep")
    var cep: String? = null

    @field:SerializedName("estado")
    var state: String? = null

    @field:SerializedName("cidade")
    var city: String? = null

    @field:SerializedName("bairro")
    var neighborhood: String? = null

    @field:SerializedName("numero")
    var number: String? = null

    @field:SerializedName("complemento")
    var complement: String? = null

    @field:SerializedName("endereco")
    var address: String? = null


}