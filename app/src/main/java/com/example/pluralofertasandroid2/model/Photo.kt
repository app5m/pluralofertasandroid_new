package com.example.pluralofertasandroid2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Photo:Serializable {
    constructor()

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("img")
    var url: String? = null

    @field:SerializedName("rows")
    var rows: String? = null
}