package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassRequestBuy(
    @SerializedName("addressUid")
    val addressUid: String,
    @SerializedName("cartUid")
    val cartUid: String,
    @SerializedName("deliveryDayFromNow")
    val deliveryDayFromNow: Int,
    @SerializedName("deliveryDayTime")
    val deliveryDayTime: Int,
    @SerializedName("discountCode")
    val discountCode: String,
    @SerializedName("withDelivery")
    val withDelivery: Int
)