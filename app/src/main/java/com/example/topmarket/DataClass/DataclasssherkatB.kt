package com.example.topmarket.DataClass

import com.google.gson.annotations.SerializedName

data class DataclasssherkatB(
    @SerializedName("cartUid")
    val cartUid: String,
    @SerializedName("withDelivery")
    val withDelivery: Int
)