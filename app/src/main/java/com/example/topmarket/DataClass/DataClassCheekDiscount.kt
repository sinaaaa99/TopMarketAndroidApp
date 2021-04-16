package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassCheekDiscount(
    @SerializedName("message")
    val message: String,
    @SerializedName("valid")
    val valid: Boolean,
    @SerializedName("value")
    val value: Int
)