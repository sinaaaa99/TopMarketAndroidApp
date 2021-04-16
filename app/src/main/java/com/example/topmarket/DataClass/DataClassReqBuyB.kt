package com.example.topmarket.DataClass

import com.google.gson.annotations.SerializedName

data class DataClassReqBuyB(
    @SerializedName("addressUid")
    val addressUid: String,
    @SerializedName("cartUid")
    val cartUid: String,
    @SerializedName("deliveryDayFromNow")
    val deliveryDayFromNow: Int,
    @SerializedName("deliveryDayTime")
    val deliveryDayTime: Int,
    @SerializedName("withDelivery")
    val withDelivery: Int
)