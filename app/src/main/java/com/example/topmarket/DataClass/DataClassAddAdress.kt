package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassAddAdress(
    @SerializedName("address")
    val address: String,
    @SerializedName("addressUid")
    val addressUid: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("title")
    val title: String
)