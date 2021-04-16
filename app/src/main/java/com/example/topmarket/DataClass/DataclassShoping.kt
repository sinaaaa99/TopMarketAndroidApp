package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassShoping(
    @SerializedName("productList")
    val productList: List<Product>
) {
    data class Product(
        @SerializedName("productUid")
        val productUid: String,
        @SerializedName("quantity")
        val quantity: Int
    )
}