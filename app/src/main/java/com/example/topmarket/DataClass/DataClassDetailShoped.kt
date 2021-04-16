package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassDetailShoped(
    @SerializedName("delivery")
    val delivery: Delivery,
    @SerializedName("payment")
    val payment: Payment,
    @SerializedName("productList")
    val productList: List<Product>,
    @SerializedName("receiver")
    val `receiver`: Receiver
) {
    data class Delivery(
        @SerializedName("deliveryDate")
        val deliveryDate: Long,
        @SerializedName("deliveryPrice")
        val deliveryPrice: Int,
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("payablePrice")
        val payablePrice: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("withDelivery")
        val withDelivery: Boolean
    )

    data class Payment(
        @SerializedName("ipgname")
        val ipgname: String,
        @SerializedName("paymentDate")
        val paymentDate: Long,
        @SerializedName("paymentTrackingNumber")
        val paymentTrackingNumber: String
    )

    data class Product(
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("pricePerProduct")
        val pricePerProduct: Int,
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

    data class Receiver(
        @SerializedName("address")
        val address: String,
        @SerializedName("fullName")
        val fullName: String,
        @SerializedName("mobileNumber")
        val mobileNumber: String,
        @SerializedName("trackingNumber")
        val trackingNumber: String
    )
}