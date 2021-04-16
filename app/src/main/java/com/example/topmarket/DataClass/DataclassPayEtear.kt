package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassPayEtear(
    @SerializedName("creationDate")
    val creationDate: Long,
    @SerializedName("factorUid")
    val factorUid: String,
    @SerializedName("orderStatus")
    val orderStatus: String,
    @SerializedName("orderStatusPersianName")
    val orderStatusPersianName: String,
    @SerializedName("orderUid")
    val orderUid: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("message")
    val message: String
)