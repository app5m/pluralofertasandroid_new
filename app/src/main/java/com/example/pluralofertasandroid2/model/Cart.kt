package com.example.pluralofertasandroid2.model
import java.io.Serializable

class Cart: Serializable {

    constructor()
    constructor(
        cuponName: String?,
        cupontValue: String?,
        cupontImage: Int
    ) {
        this.productName = cuponName
        this.productValue = cupontValue
        this.productImage = cupontImage

    }

    var productName: String? = null
    var productValue: String? = null
    var productImage = 0


}