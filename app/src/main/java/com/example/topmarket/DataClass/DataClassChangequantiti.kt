package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassChangequantiti(
    @SerializedName("cartUid")
    val cartUid: String,
    @SerializedName("credit")
    val credit: Int,
    @SerializedName("deliveryPrice")
    val deliveryPrice: Int,
    @SerializedName("itemCount")
    val itemCount: Int,
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("payablePrice")
    val payablePrice: Int,
    @SerializedName("profit")
    val profit: Int,
    @SerializedName("totalPriceWithDiscount")
    val totalPriceWithDiscount: Int,
    @SerializedName("totalPriceWithoutDiscount")
    val totalPriceWithoutDiscount: Int,
    @SerializedName("withCredit")
    val withCredit: Boolean,
    @SerializedName("withoutDelivery")
    val withoutDelivery: Boolean
) {
    data class Item(
        @SerializedName("discountPercent")
        val discountPercent: Int,
        @SerializedName("iconLink")
        val iconLink: String,
        @SerializedName("iconListLink")
        val iconListLink: String,
        @SerializedName("orderTimePrice")
        val orderTimePrice: Int,
        @SerializedName("orderTimePriceWithDiscount")
        val orderTimePriceWithDiscount: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("priceWithDiscount")
        val priceWithDiscount: Int,
        @SerializedName("productCode")
        val productCode: String,
        @SerializedName("productName")
        val productName: String,
        @SerializedName("productUid")
        val productUid: String,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("totalPrice")
        val totalPrice: Int
    )
}