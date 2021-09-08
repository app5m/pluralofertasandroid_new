package br.com.app5m.pluralofertas.model
import java.io.Serializable

class Cupon: Serializable {

    constructor()
    constructor(
        cuponName: String?,
        cuponDescription: String?,
        codCupon: String?,
        expDateCupon: String?,
        cupontValue: String?,
        cupontImage: Int
    ) {
        this.cuponName = cuponName
        this.cuponDescription = cuponDescription
        this.cupontValue = cupontValue
        this.cupontImage = cupontImage
        this.codCupon = codCupon
        this.expDateCupon = expDateCupon
    }

    var cuponName: String? = null
    var cuponDescription: String? = null
    var codCupon: String? = null
    var cupontValue: String? = null
    var cupontImage = 0
    var expDateCupon : String? = null


}