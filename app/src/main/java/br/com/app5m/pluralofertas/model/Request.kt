package br.com.app5m.pluralofertas.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Request: Serializable {

    @field:SerializedName("oferta")
    var sale: String? = null

    @field:SerializedName("parcelas")
    var installments: String? = null

    @field:SerializedName("obs")
    var obs: String? = null

    @field:SerializedName("id_cupom")
    var idCoupon: String? = null

    @field:SerializedName("valor_desc_cupom")
    var descValueCoupon: String? = null

    @field:SerializedName("valor_subtotal")
    var subTotalValue: String? = null

    @field:SerializedName("valor_total")
    var totalValue: String? = null

    @field:SerializedName("tipo_entrega")
    var typeDelivery: String? = null

    @field:SerializedName("valor_frete")
    var freightValue: String? = null

    @field:SerializedName("id_frete")
    var idFreight: String? = null

    @field:SerializedName("forma_pagamento")
    var paymentForm: String? = null

    @field:SerializedName("id_endereco")
    var idAddress: String? = null

    @field:SerializedName("id_carrinho")
    var idCart: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("card_celular")
    var cardCellphone: String? = null

    @field:SerializedName("plataforma")
    var plataform: String? = null

    @field:SerializedName("card_name")
    var cardName: String? = null

    @field:SerializedName("ano_validade")
    var yearValidity: String? = null

    @field:SerializedName("mes_validade")
    var mouthValidity: String? = null

    @field:SerializedName("card_numero")
    var cardNumber: String? = null

    @field:SerializedName("ultimos_digitos")
    var lastDigitsCard: String? = null

    @field:SerializedName("bandeira")
    var flagCard: String? = null

    @field:SerializedName("card_id")
    var cardId: String? = null

    @field:SerializedName("cvv")
    var cvv: String? = null

    @field:SerializedName("avatar")
    var avatar: String? = null

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("id_user")
    var idUser: String? = null

    @field:SerializedName("email")
    var email: String? = null

    @field:SerializedName("password")
    var password: String? = null

    @field:SerializedName("id_moip")
    var moipId: String? = null

    @field:SerializedName("genero")
    var gender: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

    @field:SerializedName("fcm")
    var fcm: String? = null

    @field:SerializedName("tipo")
    var typeUser: String? = null

    @field:SerializedName("type")
    var type: String? = null

    @field:SerializedName("tipo_pessoa")
    var typePerson: String? = null

    @field:SerializedName("tipo_pagamento")
    var typePayment: String? = null

    @field:SerializedName("card_cpf")
    var cardCpf: String? = null

    @field:SerializedName("cpf")
    var cpf: String? = null

    @field:SerializedName("data_nascimento")
    var birth: String? = null

    @field:SerializedName("data")
    var date: String? = null

    @field:SerializedName("card_nascimento")
    var cardBirth: String? = null

    @field:SerializedName("hash_card")
    var hashCard: String? = null

    @field:SerializedName("msg")
    var msg: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("nome")
    var name: String? = null

    @field:SerializedName("documento")
    var document: String? = null

    @field:SerializedName("card_complemento")
    var cardComplement: String? = null

    @field:SerializedName("card_endereco")
    var cardAddress: String? = null

    @field:SerializedName("cep")
    var cep: String? = null

    @field:SerializedName("card_cep")
    var cardCep: String? = null

    @field:SerializedName("card_cidade")
    var cardCity: String? = null

    @field:SerializedName("card_estado")
    var cardState: String? = null

    @field:SerializedName("bairro")
    var neighborhood: String? = null

    @field:SerializedName("card_bairro")
    var cardNeighborhood: String? = null

    @field:SerializedName("celular")
    var cellphone: String? = null

    @field:SerializedName("token_senha")
    var tokenPassword: String? = null

    @field:SerializedName("registration_id")
    var registrationId: String? = null

}