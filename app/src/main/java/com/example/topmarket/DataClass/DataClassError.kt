package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassError(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)