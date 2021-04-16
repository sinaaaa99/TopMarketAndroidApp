package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassListShoped(
    @SerializedName("orderList")
    val orderList: List<Order>,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
) {
    data class Order(
        @SerializedName("creationDate")
        val creationDate: String,
        @SerializedName("factorUid")
        val factorUid: String,
        @SerializedName("orderStatus")
        val orderStatus: String,
        @SerializedName("orderStatusPersianName")
        val orderStatusPersianName: String,
        @SerializedName("orderUid")
        val orderUid: String,
        @SerializedName("totalPrice")
        val totalPrice: Int
    )
}