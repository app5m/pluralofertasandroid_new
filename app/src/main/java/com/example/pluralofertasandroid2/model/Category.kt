package com.example.pluralofertasandroid2.model
import java.io.Serializable

class Category: Serializable {
    var categoryName: String? = null


    constructor()
    constructor(
        //id: Int,
        categoryName: String?

    ) {
        this.categoryName = "só pega"
       // this.id = id


    }

    fun getNameCategory(): String? {
        return categoryName
    }

    fun setNameCategory(categoryName: String?) {
        this.categoryName = categoryName
    }


}