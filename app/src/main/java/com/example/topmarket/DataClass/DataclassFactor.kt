package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassFactor(
    @SerializedName("address")
    val address: String,
    @SerializedName("clientName")
    val clientName: String,
    @SerializedName("creationDate")
    val creationDate: Long,
    @SerializedName("deliveryPrice")
    val deliveryPrice: Int,
    @SerializedName("factorNumber")
    val factorNumber: Int,
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("payablePrice")
    val payablePrice: Int,
    @SerializedName("tax")
    val tax: Int,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("totalPriceWithDiscount")
    val totalPriceWithDiscount: Int
) {
    data class Item(
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("itemCode")
        val itemCode: String,
        @SerializedName("itemName")
        val itemName: String,
        @SerializedName("itemTotalCount")
        val itemTotalCount: Int,
        @SerializedName("pricePerItem")
        val pricePerItem: Int,
        @SerializedName("priceWithDiscountPerItem")
        val priceWithDiscountPerItem: Int,
        @SerializedName("totalPrice")
        val totalPrice: Int,
        @SerializedName("totalPriceWithDiscount")
        val totalPriceWithDiscount: Int
    )
}