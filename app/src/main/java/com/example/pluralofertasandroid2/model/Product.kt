package com.example.pluralofertasandroid2.model

import java.io.Serializable

class Product: Serializable {

    constructor()
    constructor(
        productName: String?,
        productDescription: String?,
        asOf: String?,
        productValue: String?,
        productImage: Int
    ) {
        this.productName = productName
        this.productDescription = productDescription
        this.asOf = asOf
        this.productValue = productValue
        this.productImage = productImage
    }

     var productName: String? = null
     var productDescription: String? = null
     var asOf: String? = null
     var productValue: String? = null
     var productImage = 0






}