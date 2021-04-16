package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassTaxfif(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
) {
    data class Data(
        @SerializedName("code")
        val code: String,
        @SerializedName("creationDate")
        val creationDate: Long,
        @SerializedName("description")
        val description: String,
        @SerializedName("discountPercent")
        val discountPercent: Int,
        @SerializedName("expiryDate")
        val expiryDate: Long,
        @SerializedName("iconLink")
        val iconLink: String,
        @SerializedName("iconListLink")
        val iconListLink: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("priceWithDiscount")
        val priceWithDiscount: Int,
        @SerializedName("productUid")
        val productUid: String,
        @SerializedName("quantity")
        val quantity: Int
    )
}