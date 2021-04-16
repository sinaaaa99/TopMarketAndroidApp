package com.example.topmarket.DataClass

import com.google.gson.annotations.SerializedName

data class DataClassSherkatdis (
    @SerializedName("cartUid")
    val cartUid: String,
    @SerializedName("discountCode")
    val discountCode: String,
    @SerializedName("withDelivery")
    val withDelivery: Int
        )