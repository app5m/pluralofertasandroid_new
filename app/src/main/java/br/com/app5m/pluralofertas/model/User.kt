package br.com.app5m.pluralofertas.model

import com.google.gson.annotations.SerializedName

class User {


    @field:SerializedName("nome_cartao")
    var cardName: String? = null

    @field:SerializedName("ano_validade")
    var yearValidity: String? = null

    @field:SerializedName("mes_validade")
    var mouthValidity: String? = null

    @field:SerializedName("numero_cartao")
    var cardNumber: String? = null

    @field:SerializedName("ultimos_digitos")
    var lastDigitsCard: String? = null

    @field:SerializedName("bandeira")
    var flagCard: String? = null

    @field:SerializedName("card_id")
    var cardId: String? = null

    @field:SerializedName("cvv")
    var cvv: String? = null

    @field:SerializedName("data_nascimento")
    var birth: String? = null

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("id_user")
    var idUser: String? = null

    @field:SerializedName("celular")
    var cellphone: String? = null

    @field:SerializedName("id_moip")
    var moipId: String? = null

    @field:SerializedName("email")
    var email: String? = null

    @field:SerializedName("avatar")
    var avatar: String? = null

    @field:SerializedName("password")
    var password: String? = null

    @field:SerializedName("cpf")
    var cpf: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

    @field:SerializedName("fcm")
    var fcm: String? = null

    @field:SerializedName("type")
    var type: String? = null

    @field:SerializedName("registration_id")
    var registrationId: String? = null

    @field:SerializedName("msg")
    var msg: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("nome")
    var name: String? = null

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