package com.example.pluralofertasandroid2.model
import java.io.Serializable

class Shooping: Serializable {

    constructor()
    constructor(
        shoopingId: String?,
        shoopingName: String?,
        shoopingValue: String?,
        shoopingStatus: String?,
        shoopingDate: String?,
        shoopingImage: Int
    ) {
        this.productName = productName
        this.shoopingId = shoopingId
        this.shoopingValue = shoopingValue
        this.shoopingStatus = shoopingStatus
        this.shoopingDate = shoopingDate
        this.productImage = productImage

    }

    var shoopingId: String? = null
    var productName: String? = null
    var shoopingValue: String? = null
    var shoopingStatus: String? = null
    var shoopingDate: String? = null
    var productImage = 0


}